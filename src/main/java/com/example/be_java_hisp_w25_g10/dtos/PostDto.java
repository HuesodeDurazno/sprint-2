package com.example.be_java_hisp_w25_g10.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Date;

public record PostDto(
        @Positive
        int userId,
        @Positive
        int postId,
        LocalDate date,
        @Valid
        ProductDto product){

}
