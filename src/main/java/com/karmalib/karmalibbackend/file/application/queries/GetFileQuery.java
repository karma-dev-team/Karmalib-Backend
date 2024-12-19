package com.karmalib.karmalibbackend.file.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Data;

import java.util.UUID;

@Data
public class GetFileQuery extends BaseQuery {
    private UUID fileId;
}
