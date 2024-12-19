package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetChapterQuery extends BaseQuery {
    private UUID chapterId;
    private UUID authorId;
}
