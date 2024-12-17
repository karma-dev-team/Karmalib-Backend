package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@Builder
public class GetTitleQuery extends BaseQuery {
    @NonNull
    private UUID titleId;
}