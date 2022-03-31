package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.core.Exception.CalendarException;
import com.hyunjung.finalproject.core.Exception.ErrorCode;
import com.hyunjung.finalproject.core.domain.RequestStatus;
import com.hyunjung.finalproject.core.domain.entity.RequestReplyType;
import com.hyunjung.finalproject.core.domain.entity.repository.EngagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EngagementService {
    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        //engagement 조회
        //참석자가 auth user와 같은지 비교
        //requested 상태인지 체크
        //update
        return engagementRepository.findById(engagementId)
                .filter(e -> e.getRequestStatus() == RequestStatus.REQUESTED)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(type))
                .orElseThrow(()-> new CalendarException(ErrorCode.BAD_REQUEST))
                .getRequestStatus();
    }
}
