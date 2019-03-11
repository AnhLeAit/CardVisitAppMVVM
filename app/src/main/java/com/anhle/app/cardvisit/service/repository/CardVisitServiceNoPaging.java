package com.anhle.app.cardvisit.service.repository;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.CardFoo;

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
public interface CardVisitServiceNoPaging {

    String HTTPS_API_URL = "https://demo8104666.mockable.io";

    @GET("/cards")
    Single<Response<List<CardFoo>>> getCards();

    // Just replace real API here, now it does not exist!
    @GET("/cards/{id}")
    Single<Response<Card>> getCardDetails(@Path("id") long id);

}
