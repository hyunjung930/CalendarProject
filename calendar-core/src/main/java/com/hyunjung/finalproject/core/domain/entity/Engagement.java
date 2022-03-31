package com.hyunjung.finalproject.core.domain.entity;

import com.hyunjung.finalproject.core.domain.Event;
import com.hyunjung.finalproject.core.domain.RequestStatus;
import com.hyunjung.finalproject.core.domain.ScheduleType;
import com.hyunjung.finalproject.core.util.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "engagement")
@Entity
public class Engagement extends BaseEntity{


    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "attendee_id")
    @ManyToOne
    private User attendee;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;


    public Event getEvent() {
        return  schedule.toEvent(); }

    public boolean isOverlapped(Period period) {
        return this.schedule.isOverlapped(period);
    }

    public Engagement reply(RequestReplyType type) {
        switch (type){
            case ACCEPT:
                this.requestStatus = RequestStatus.ACCEPTED;
                break;
            case REJECT:
                this.requestStatus = RequestStatus.REJECTED;
        }
        return this;
    }
}
