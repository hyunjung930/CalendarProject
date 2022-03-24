package com.hyunjung.finalproject.core.domain;

import com.hyunjung.finalproject.core.domain.entity.Engagement;
import com.hyunjung.finalproject.core.domain.entity.Schedule;
import com.hyunjung.finalproject.core.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
public class Event {

    private Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return schedule.getStartAt().isBefore(endAt) && startAt.isBefore(schedule.getEndAt());
    }
}
