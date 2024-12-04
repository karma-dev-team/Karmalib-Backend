package com.karmalib.karmalibbackend.file.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GetFilesListQuery extends BaseQuery {
    private List<UUID> fileIds;
}
