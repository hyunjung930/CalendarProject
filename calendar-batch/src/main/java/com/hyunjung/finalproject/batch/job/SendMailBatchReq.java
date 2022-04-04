package com.hyunjung.finalproject.batch.job;


import lombok.*;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SendMailBatchReq {
    private Long id;    //schedule id
    private LocalDateTime startAt;
    private String title;
    private String userMail;
}
