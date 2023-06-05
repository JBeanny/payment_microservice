package com.example.payment.util;

import com.example.payment.model.Bundle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BundleSerializer {

    public Bundle serialize(String jsonString) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        String str = obj.readTree(jsonString).get("bundle").toString();

        return obj.readValue(str,Bundle.class);
    }

    public Integer bundleDurationSerializer(String duration) {
        String[] bundleDuration = duration.split("d");
        return Integer.valueOf(bundleDuration[0]);
    }
}
