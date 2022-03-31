package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.api.dto.EngagementEmailStuff;
import com.hyunjung.finalproject.api.dto.EventCreatReq;
import com.hyunjung.finalproject.core.Exception.CalendarException;
import com.hyunjung.finalproject.core.Exception.ErrorCode;
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
import java.util.stream.Collectors;

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
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }
        final Schedule eventSchedule = Schedule.event(
                eventCreatReq.getTitle(),
                eventCreatReq.getDescription(),
                eventCreatReq.getStartAt(),
                eventCreatReq.getEndAt(),
                userService.findByUserId(authUser.getId())
        );
        scheduleRepository.save(eventSchedule);
        final List<User> attendees =
                eventCreatReq.getAttendeeIds().stream()
                        .map(userService::findByUserId)
                        .collect(Collectors.toList());
                attendees.forEach(attendee -> {
                    final Engagement engagement = Engagement.builder()
                            .schedule(eventSchedule)
                            .requestStatus(RequestStatus.REQUESTED)
                            .attendee(attendee)
                            .build();
                    engagementRepository.save(engagement);
                    emailService.sendEngagement(EngagementEmailStuff.builder()
                                    .engagementId(engagement.getId())
                                    .title(engagement.getEvent().getTitle())
                                    .toEmail(engagement.getAttendee().getEmail())
                                    .attendeeEmails(attendees.stream()
                                            .map(User::getEmail)
                                            .collect(Collectors.toList()))
                                    .period(engagement.getEvent().getPeriod())
                            .build());
                });
    }
}