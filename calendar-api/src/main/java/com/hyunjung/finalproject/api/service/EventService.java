package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.api.dto.EventCreatReq;
import com.hyunjung.finalproject.core.domain.RequestStatus;
import com.hyunjung.finalproject.core.domain.entity.Engagement;
import com.hyunjung.finalproject.core.domain.entity.Schedule;
import com.hyunjung.finalproject.core.domain.entity.User;
import com.hyunjung.finalproject.core.domain.entity.repository.EngagementRepository;
import com.hyunjung.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.hyunjung.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EmailService emailService;
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;

    @Transactional
    public void creat(EventCreatReq eventCreatReq, AuthUser authUser) {
        //이벤트 참여자의 다른 이벤트와 중복이 되면 안된다.
        //1-2까지 회의가 있는데, 추가로 다른 회의를 등록할 수 없음.
        // 추가로 이메일 발송
        final List<Engagement> engagementList = engagementRepository.findAll();    //TODO findALL 개선...
        if(engagementList.stream()
                .anyMatch(e -> eventCreatReq.getAttendeeIds().contains(e.getAttendee().getId())
                        && e.getRequestStatus() == RequestStatus.ACCEPTED
                        && e.getEvent().isOverlapped(
                        eventCreatReq.getStartAt(),
                        eventCreatReq.getEndAt()))
        ){
            throw new RuntimeException("cannot make engagement. period overlapped! ");
        }
        final Schedule eventSchedule = Schedule.event(
                eventCreatReq.getTitle(),
                eventCreatReq.getDescription(),
                eventCreatReq.getStartAt(),
                eventCreatReq.getEndAt(),
                userService.findByUserId(authUser.getId())
        );
        scheduleRepository.save(eventSchedule);
        eventCreatReq.getAttendeeIds()
                .forEach(atId -> {
                    final User attendee = userService.findByUserId(atId);
                    final Engagement engagement = Engagement.builder()
                            .schedule(eventSchedule)
                            .requestStatus(RequestStatus.REQUESTED)
                            .attendee(attendee)
                            .build();
                    engagementRepository.save(engagement);
                    emailService.sendEngagement(engagement);
                });
    }
}