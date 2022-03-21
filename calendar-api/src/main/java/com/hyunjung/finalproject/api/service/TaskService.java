package com.hyunjung.finalproject.api.service;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.api.dto.TaskCreatReq;
import com.hyunjung.finalproject.core.domain.entity.Schedule;
import com.hyunjung.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.hyunjung.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private  final UserService userService;
    private  final ScheduleRepository scheduleRepository;

    public void creat(TaskCreatReq taskCreatReq, AuthUser authUser) {
        final Schedule taskSchedule =
                Schedule.task(taskCreatReq.getTitle(),
                        taskCreatReq.getDescription(),
                        taskCreatReq.getTaskAt(),
                        userService.findByUserId(authUser.getId()));
        scheduleRepository.save(taskSchedule);

    }
}
