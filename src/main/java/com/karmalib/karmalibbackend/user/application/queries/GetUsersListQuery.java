package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import com.karmalib.karmalibbackend.common.application.QueryWithPagination;
import com.karmalib.karmalibbackend.user.domain.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetUsersListQuery extends QueryWithPagination {
    @Schema(nullable = true)
    private Boolean blocked;
    @Schema(nullable = true)
    private List<UserRole> roles;
}
