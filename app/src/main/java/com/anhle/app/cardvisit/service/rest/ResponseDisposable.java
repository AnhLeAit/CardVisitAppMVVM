package com.anhle.app.cardvisit.service.rest;

import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

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
 * Transform response into DisposableSingleObserver content ApiResponse success or error
 * which ready to use for LiveData. People just setValue(ApiResponse<T>) and LiveData
 * take care notify data change to observers (View layer)
 * @param <T> the received value type
 */
public abstract class ResponseDisposable<T extends Response> extends DisposableSingleObserver<T> {

    @Override
    public void onSuccess(T response) {
        if (response != null) {
            if (response.isSuccessful()) {
                onRequestDone(ApiResponse.success(response.body(), response.headers()));
            } else {
                RestError restError = new RestError(response.code());
                if (response.errorBody() != null) {
                    try {
                        restError.setMessage(response.errorBody().string());
                    } catch (Exception e) {
                        restError.setMessage(e.toString());
                    }
                }
                onRequestDone(ApiResponse.error(restError));
            }
        } else {
            // Should never reach this code...
            onRequestDone(ApiResponse.error(new RestError(0, "Response NUll")));
        }
    }

    @Override
    public void onError(Throwable e) {
        RestError restError = new RestError(0, e.toString());
        onRequestDone(ApiResponse.error(restError));
    }

    /**
     * Push response.body() into ApiResponse after REST-ful request done
     * with Success or Error body.
     *
     * @param response
     */
    public abstract void onRequestDone(ApiResponse<?> response);
}