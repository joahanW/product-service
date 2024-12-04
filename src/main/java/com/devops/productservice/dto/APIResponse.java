package com.devops.productservice.dto;

import com.devops.productservice.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Schema(name = "Generic API Response",
        description = "Schema to hold API Response information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    @Schema(
            description = "Status response of API", example = "SUCCESS or FAILED"
    )
    private String status;

    @Schema(
            description = "Error Message validation of request body", example = "[{'field':'message'}]"
    )
    private List<HashMap<String, String>> message = new ArrayList<>();

    @Schema(
            description = "Data response of API"
    )
    private T data;

    public APIResponse(T data) {
        this.status = CommonStatus.SUCCESS.name();
        this.data = data;
    }

    public APIResponse(List<HashMap<String, String>> message) {
        this.status = CommonStatus.FAILED.name();
        this.message = message;
    }
}
