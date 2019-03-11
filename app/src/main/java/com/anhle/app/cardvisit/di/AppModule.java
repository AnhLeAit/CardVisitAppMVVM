package com.anhle.app.cardvisit.di;

import android.app.Application;

import com.anhle.app.cardvisit.service.repository.CardVisitServiceNoPaging;
import com.anhle.app.cardvisit.service.repository.CardVisitServicePaging;
import com.anhle.app.cardvisit.service.rest.RestLogger;
import com.anhle.app.cardvisit.service.room.AppDatabase;
import com.anhle.app.cardvisit.service.room.CardDao;
import com.anhle.app.cardvisit.utils.AppConstants;
import com.anhle.app.cardvisit.viewmodel.CardViewModelFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(RestLogger.createHttpLoggingInterceptor())
                .build();
    }

    @Singleton
    @Provides
    static GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    static RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    static CardVisitServiceNoPaging provideCardVisitServiceNoPaging(OkHttpClient okHttpClient, GsonConverterFactory gsonConverter, RxJava2CallAdapterFactory rxCallAdapter) {
        return new Retrofit.Builder()
                .baseUrl(CardVisitServiceNoPaging.HTTPS_API_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(rxCallAdapter)
                .build()
                .create(CardVisitServiceNoPaging.class);
    }

    @Singleton
    @Provides
    static CardVisitServicePaging provideCardVisitServicePaging(OkHttpClient okHttpClient, GsonConverterFactory gsonConverter, RxJava2CallAdapterFactory rxCallAdapter) {
        return new Retrofit.Builder()
                .baseUrl(CardVisitServicePaging.HTTPS_API_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(rxCallAdapter)
                .build()
                .create(CardVisitServicePaging.class);
    }

    @Singleton
    @Provides
    static ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new CardViewModelFactory(viewModelSubComponent.build());
    }

    @Singleton
    @Provides
    static CardDao provideCardDao(Application application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        return db.cardDao();
    }
}
