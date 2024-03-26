package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;

import java.util.List;

public interface LikeFolderService {

    void addLikeFolder(LikeFolderDto likeFolderDto, String memberUuid);
    List<LikeFolderDto> findLikeFolderList(String memberUuid);

}

