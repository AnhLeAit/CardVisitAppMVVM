package com.anhle.app.cardvisit.service.repository;

import com.anhle.app.cardvisit.service.model.Card;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
 **/
public interface CardVisitServicePaging {

    String HTTPS_API_URL = "https://demo7527907.mockable.io";

    @GET("/cards/{pageIndex}")
    Single<Response<List<Card>>> getCards(@Path("pageIndex") int pageIndex);
}
