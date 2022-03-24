package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.core.domain.entity.Engagement;

public interface EmailService {

    void sendEngagement(Engagement engagement);
}
