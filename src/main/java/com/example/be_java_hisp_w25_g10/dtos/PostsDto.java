package com.example.be_java_hisp_w25_g10.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PostsDto (
        @Positive
        int userId,
        @NotEmpty
        @Valid
        List<PostDto> posts) { }
