package com.nocaffeine.ssgclone.like.vo.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductLikeRemoveRequestVo {
    private Long likeFolderId;
    private List<Long> productLikeId;

}
