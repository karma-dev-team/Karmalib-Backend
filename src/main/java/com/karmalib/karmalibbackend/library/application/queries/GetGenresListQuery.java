package com.karmalib.karmalibbackend.library.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetGenresListQuery extends BaseQuery {
    @Nullable
    private Boolean visibleOnly;
}