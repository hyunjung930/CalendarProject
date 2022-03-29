package com.hyunjung.finalproject.api.util;

import com.hyunjung.finalproject.api.dto.EventDto;
import com.hyunjung.finalproject.api.dto.NotificationDto;
import com.hyunjung.finalproject.api.dto.ScheduleDto;
import com.hyunjung.finalproject.api.dto.TaskDto;
import com.hyunjung.finalproject.core.domain.entity.Schedule;

public abstract class DtoConverter { //abstract를 사용해서 생성자를 만들지 못하게 한다.

    public static ScheduleDto fromSchedule(Schedule schedule){
        switch (schedule.getScheduleType()){
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();

            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .taskAt(schedule.getStartAt())
                        .description(schedule.getDescription())
                        .writerId(schedule.getId())
                        .title(schedule.getTitle())
                        .build();

            case NOTIFICATION:
                return NotificationDto.builder()
                        .notifyAt(schedule.getStartAt())
                        .scheduleId(schedule.getId())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            default:
                throw new RuntimeException("bad request. not matched schedule type");
        }
    }
}
