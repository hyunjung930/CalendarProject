package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.api.dto.ScheduleDto;
import com.hyunjung.finalproject.api.util.DtoConverter;
import com.hyunjung.finalproject.core.domain.entity.Engagement;
import com.hyunjung.finalproject.core.domain.entity.Schedule;
import com.hyunjung.finalproject.core.domain.entity.repository.EngagementRepository;
import com.hyunjung.finalproject.core.domain.entity.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional(readOnly = true)
//readOnly = true 읽기만 가능한 데베에 접근하게 만들어주는 역할도 함.
@Service
public class ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;

    public List<ScheduleDto> getScheduleByDay(AuthUser authUser, LocalDate date) {
        return Stream.concat(
                        scheduleRepository.findAllByWriter_Id(authUser.getId())
                                .stream()
                                .filter(schedule -> schedule.isOverlapped(date))
                                .map(DtoConverter::fromSchedule),  //특정 날짜와 겹치는 것만 필터링 한다.
                         engagementRepository.findAllByAttendee_Id(authUser.getId())
                                .stream()
                                .filter(engagement -> engagement.isOverlapped(date))
                                .map(engagement -> DtoConverter.fromSchedule(engagement.getSchedule())))
                .collect(Collectors.toList());
    }
}
