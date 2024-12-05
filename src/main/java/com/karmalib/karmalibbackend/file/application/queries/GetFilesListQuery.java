package com.karmalib.karmalibbackend.file.application.queries;

import com.karmalib.karmalibbackend.common.application.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GetFilesListQuery extends BaseQuery {
    private List<UUID> fileIds;
}
