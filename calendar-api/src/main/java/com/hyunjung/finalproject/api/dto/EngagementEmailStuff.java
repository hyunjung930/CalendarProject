package com.hyunjung.finalproject.api.dto;

import com.hyunjung.finalproject.core.util.Period;
import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngagementEmailStuff {
    private static final String engagementUpdateApi = "http://localhost:8080/events/engagements/";
    private final Long engagementId;
    private final String toEmail;
    private final List<String> attendeeEmails;
    private final String title;
    private final Period period;

    @Builder
    public EngagementEmailStuff(Long engagementId, String toEmail, List<String> attendeeEmails, String title, Period period) {
        this.engagementId = engagementId;
        this.toEmail = toEmail;
        this.attendeeEmails = attendeeEmails;
        this.title = title;
        this.period = period;
    }

    public String getSubject(){
        return new StringBuilder()
                .append("[초대장] ")
                .append(title)
                .append(" - ")
                .append(period.toString())
                .append("(")
                .append(toEmail)
                .append(")")
                .toString();
    }
    public String getToEmail(){
        return this.toEmail;
    }

    public Map<String, Object> getProps() {
        final Map<String, Object> props =  new HashMap<>();
        props.put("title",title);
        props.put("calendar", toEmail);
        props.put("period", period.toString());
        props.put("attendees", attendeeEmails);
        props.put("acceptUrl", engagementUpdateApi + engagementId + "?type=ACCEPT"); // 특정 화면을 여는 주소를 넣어줌
        props.put("rejectUrl", engagementUpdateApi + engagementId + "?type=REJECT");
        return props;
    }
}
