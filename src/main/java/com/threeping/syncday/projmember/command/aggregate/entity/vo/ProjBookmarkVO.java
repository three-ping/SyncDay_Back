package com.threeping.syncday.projmember.command.aggregate.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.projmember.command.aggregate.entity.BookmarkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjBookmarkVO {

    @JsonProperty("proj_member_id")
    private Long projMemberId;

    @JsonProperty("boomark_status")
    private BookmarkStatus bookmarkStatus;
}
