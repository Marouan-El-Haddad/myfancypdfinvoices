package com.marouan.myfancypdfinvoices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record Invoice(
        String id,
        @JsonProperty("user_id") String userId,
        Integer amount,
        @JsonProperty("pdf_url") String pdfUrl) {

    public Invoice(String userId, Integer amount, String pdfUrl) {
        this(UUID.randomUUID().toString(), userId, amount, pdfUrl);
    }
}