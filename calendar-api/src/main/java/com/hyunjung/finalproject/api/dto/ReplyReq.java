package com.hyunjung.finalproject.api.dto;

import com.hyunjung.finalproject.core.domain.entity.RequestReplyType;

public class ReplyReq {
    private  RequestReplyType type; //REJECT, ACCEPT

    public  ReplyReq() {

    }
    public ReplyReq(RequestReplyType type) {
        this.type = type;
    }

    public RequestReplyType getType() {
        return type;
    }
}
