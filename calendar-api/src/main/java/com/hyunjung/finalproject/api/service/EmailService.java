package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.EngagementEmailStuff;
import com.hyunjung.finalproject.core.domain.entity.Engagement;

public interface EmailService {

    void sendEngagement(EngagementEmailStuff stuff);
}
