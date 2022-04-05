package com.hyunjung.finalproject.api.dto;

import com.hyunjung.finalproject.core.domain.entity.Share;
import lombok.Data;

@Data
public class CreateShareReq {

    private final Long toUserId;
    private final Share.Direction direction;
}
