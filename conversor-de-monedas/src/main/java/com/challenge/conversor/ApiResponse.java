package com.challenge.conversor;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ApiResponse {

    @SerializedName("result")
    private String result;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    // Getters
    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    // toString para una fácil depuración
    @Override
    public String toString() {
        return "ApiResponse{" +
                "result='" + result + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", conversionRates=" + conversionRates +
                '}';
    }
}
