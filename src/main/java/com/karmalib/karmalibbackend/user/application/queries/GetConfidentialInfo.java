package com.karmalib.karmalibbackend.user.application.queries;

import com.karmalib.karmalibbackend.common.application.IQueryHandler;
import com.karmalib.karmalibbackend.user.application.queries.models.ConfidentialInfoModel;
import org.springframework.cache.annotation.Cacheable;

public class GetConfidentialInfo implements IQueryHandler<GetConfidentialInfoQuery, ConfidentialInfoModel> {
    @Override
    public ConfidentialInfoModel handle(GetConfidentialInfoQuery o) {


        return null;
    }
}