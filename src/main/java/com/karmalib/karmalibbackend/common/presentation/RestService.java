package com.karmalib.karmalibbackend.common.presentation;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class RestService {
    public static ResponseEntity<CustomResponseEntity> buildResponse(CommandResult result) {
        CustomResponseEntity response = CustomResponseEntity.of(
                result.getIsSuccess(),
                UUID.fromString(result.getId()),
                result.getStatus(),
                result.getMessage()
        );
        return ResponseEntity.status(result.getIsSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
