package com.hyunjung.finalproject.api.controller;

import com.hyunjung.finalproject.api.dto.*;
import com.hyunjung.finalproject.api.service.EventService;
import com.hyunjung.finalproject.api.service.NotificationService;
import com.hyunjung.finalproject.api.service.ScheduleQueryService;
import com.hyunjung.finalproject.api.service.TaskService;
import com.hyunjung.finalproject.core.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

import static com.hyunjung.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final ScheduleQueryService scheduleQueryService;
    //조회 메서드를 각각 만드는 것 보다 조회 메소드를 만들어 관리
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

    @GetMapping("/day")
    public List<ScheduleDto> getScheduleByDay(
            AuthUser authUser,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleQueryService.getScheduleByDay(authUser, date == null ? LocalDate.now() : date);
        //date가 필수값(required = false)이 아니기 때문에 null 일수도 있기 때문에 null일 경우도 추가해줬음.

    }
}
