package com.johanwork.product_service.mapper;

import com.johanwork.product_service.dto.ApiResponse;
import org.apache.coyote.Response;

import java.util.List;

public interface ApiResponseMapper <M, R, S>{

    M requestToModel(R request);
    S modelToResponse(M model);
    List<S> listModelToListResponse(List<M> listModel);

    default ApiResponse<S> mapToApiResponse(M model) {
        var response = modelToResponse(model);
        return new ApiResponse<>(response);
    }

    default ApiResponse<List<S>> mapToListApiResponse(List<M> listModel) {
        var response = listModelToListResponse(listModel);
        return new ApiResponse<>(response);
    }

}
