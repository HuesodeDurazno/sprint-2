package com.example.be_java_hisp_w25_g10.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CountDto (

        @Positive
        int user_id,
        @NotNull
        @Size(max = 15)
        String user_name,
        @Positive
        int followers_count) {
}
