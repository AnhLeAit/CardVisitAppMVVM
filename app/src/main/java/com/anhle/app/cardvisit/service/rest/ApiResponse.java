package com.anhle.app.cardvisit.service.rest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.Headers;

/**
 * Copyright 2019 (C) Lê Ngọc Anh
 * github: https://github.com/AnhLeAit
 * email: anhle.ait@gmail.com
 *
 * <p>
 * Created on : 12 Mar, 2019
 * Author     : anhle
 * <p>
 * -----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 * -----------------------------------------------------------------------------
 * VERSION     AUTHOR/           DESCRIPTION OF CHANGE
 * OLD/NEW     DATE              RFC NO
 * -----------------------------------------------------------------------------
 * --/1.0    | anhle           | Initial Create.
 *           | 12 Mar, 2019    |
 * ----------|-----------------|------------------------------------------------
 *           | Author          | Defect ID 1/Description
 *           | Date            |
 * ----------|-----------------|------------------------------------------------
 *
 * Generic class that contains data and status about loading this data.
 */
public class ApiResponse<T> {

    public final Status status;

    // Success data response - It generic with any type of data Model
    @Nullable
    public final T data;

    // Full info about the error
    @Nullable public final RestError error;

    // Some API need return info set in header like Spring framework provide
    // lazy load pageable API return 'X-Total-Count' inside Header.
    @Nullable public final Headers headers;

    private ApiResponse(@NonNull Status status, @Nullable T data, @Nullable Headers headers, @Nullable RestError error) {
        this.status = status;
        this.data = data;
        this.headers = headers;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(@NonNull T data, @Nullable Headers headers) {
        return new ApiResponse<>(Status.SUCCESS, data, headers,  null);
    }

    public static <T> ApiResponse<T> error(RestError error) {
        return new ApiResponse<>(Status.ERROR, null, null, error);
    }

    public static <T> ApiResponse<T> loading(@Nullable T data) {
        return new ApiResponse<>(Status.LOADING, data, null, null);
    }
}
