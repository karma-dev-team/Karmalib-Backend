package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetUserQuery extends BaseQuery {
    private UUID userId = null;
    @Email
    private String email = null;
}
