package com.hyunjung.finalproject.api.controller;

import com.hyunjung.finalproject.api.dto.AuthUser;
import com.hyunjung.finalproject.api.dto.TaskCreatReq;
import com.hyunjung.finalproject.api.service.TaskService;
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

    @PostMapping("/tasks")
    public ResponseEntity<Void> creatTask(
            @RequestBody TaskCreatReq taskCreatReq,
            AuthUser authUser){
        taskService.creat(taskCreatReq, authUser);
        return ResponseEntity.ok().build();
    }
}
