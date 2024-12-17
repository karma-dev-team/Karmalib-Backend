package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
@Builder
public class GetAuthorQuery extends BaseQuery {
    @NotNull
    private UUID authorId;
}