package com.example.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bundle {

    @JsonProperty("bundleId")
    private Long bundleId;

    @JsonProperty("bundleName")
    private String bundleName;

    @JsonProperty("bundleDuration")
    private String bundleDuration;

    @JsonProperty("bundlePrice")
    private Integer bundlePrice;

    @JsonProperty("bundleData")
    private Long bundleData;

    @JsonProperty("autoRenewal")
    private boolean autoRenewal;
}
