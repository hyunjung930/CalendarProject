package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.api.dto.NotificationCreatReq;
import com.hyunjung.finalproject.core.domain.entity.Schedule;
import com.hyunjung.finalproject.core.domain.entity.User;
import com.hyunjung.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.hyunjung.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void creat(NotificationCreatReq notificationCreatReq, AuthUser authUser) {
        final User user = userService.findByUserId(authUser.getId());
        final List<LocalDateTime> notifyAtList = notificationCreatReq.getRepeatTimes();
        notifyAtList.forEach(notifyAt -> {
            final Schedule notificationSchedule =
                    Schedule.notification(
                            notificationCreatReq.getTitle(),
                            notifyAt,
                            user);
            scheduleRepository.save(notificationSchedule);
        });

    }
}
