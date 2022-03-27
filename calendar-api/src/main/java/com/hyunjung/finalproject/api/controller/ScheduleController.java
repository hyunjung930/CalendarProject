package com.hyunjung.finalproject.api.controller;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.api.dto.EventCreatReq;
import com.hyunjung.finalproject.api.dto.NotificationCreatReq;
import com.hyunjung.finalproject.api.dto.TaskCreatReq;
import com.hyunjung.finalproject.api.service.EventService;
import com.hyunjung.finalproject.api.service.NotificationService;
import com.hyunjung.finalproject.api.service.TaskService;
import com.hyunjung.finalproject.core.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.hyunjung.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> creatTask(
            @RequestBody TaskCreatReq taskCreatReq,
            AuthUser authUser){
        taskService.creat(taskCreatReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/events")
    public ResponseEntity<Void> creatEvent(
            @RequestBody EventCreatReq eventCreatReq,
            AuthUser authUser){
        eventService.creat(eventCreatReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Void> creatNotifications(
            @RequestBody NotificationCreatReq notificationCreatReq,
            AuthUser authUser){
        notificationService.creat(notificationCreatReq, authUser);
        return ResponseEntity.ok().build();
    }
}
