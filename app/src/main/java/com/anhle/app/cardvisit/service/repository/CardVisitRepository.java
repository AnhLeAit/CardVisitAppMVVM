package com.anhle.app.cardvisit.service.repository;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.CardFoo;
import com.anhle.app.cardvisit.service.rest.ApiResponse;
import com.anhle.app.cardvisit.service.rest.ResponseDisposable;
import com.anhle.app.cardvisit.service.room.CardDao;
import com.anhle.app.cardvisit.service.room.CardEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
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
 **/
@Singleton
public class CardVisitRepository {

    private CardVisitServiceNoPaging cardVisitServiceNoPaging;
    private CardVisitServicePaging cardVisitServicePaging;
    private final CardDao cardDao;

    @Inject
    public CardVisitRepository(CardVisitServiceNoPaging cardVisitServiceNoPaging, CardVisitServicePaging cardVisitServicePaging, CardDao cardDao) {
        this.cardVisitServiceNoPaging = cardVisitServiceNoPaging;
        this.cardVisitServicePaging = cardVisitServicePaging;
        this.cardDao = cardDao;
    }

    public MutableLiveData<ApiResponse<List<CardFoo>>> getCardsNoPaging() {
        final MutableLiveData<ApiResponse<List<CardFoo>>> data = new MutableLiveData<>();

        cardVisitServiceNoPaging.getCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> data.setValue(ApiResponse.loading(null)))
                .subscribe(new ResponseDisposable<Response<List<CardFoo>>>() {
                    @Override
                    public void onRequestDone(ApiResponse<?> response) {
                        data.setValue((ApiResponse<List<CardFoo>>) response);
                    }
                });

        return data;
    }

    public MutableLiveData<ApiResponse<List<Card>>> getCardsPaging(int pageIndex) {
        final MutableLiveData<ApiResponse<List<Card>>> data = new MutableLiveData<>();

        cardVisitServicePaging.getCards(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> data.setValue(ApiResponse.loading(null)))
                .subscribe(new ResponseDisposable<Response<List<Card>>>() {
                    @Override
                    public void onRequestDone(ApiResponse<?> response) {
                        data.setValue((ApiResponse<List<Card>>) response);
                    }
                });

        return data;
    }

    public LiveData<ApiResponse<Card>> getCardDetails(long cardID) {
        final MutableLiveData<ApiResponse<Card>> data = new MutableLiveData<>();
        cardVisitServiceNoPaging.getCardDetails(cardID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> data.setValue(ApiResponse.loading(null)))
                .subscribe(new ResponseDisposable<Response<Card>>() {
                               @Override
                               public void onRequestDone(ApiResponse<?> response) {
                                   data.setValue((ApiResponse<Card>) response);
                               }
                           }
                );

        return data;
    }

    public MutableLiveData<Card> createAnEmptyCard() {
        final MutableLiveData<Card> data = new MutableLiveData<>();
        data.setValue(new Card());
        return data;
    }

    public MutableLiveData<List<CardEntity>> getAllCardSaved() {
        final MutableLiveData<List<CardEntity>> data = new MutableLiveData<>();

        cardDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<CardEntity>>() {
                               @Override
                               public void onSuccess(List<CardEntity> cardEntities) {
                                   data.setValue(cardEntities);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   data.setValue(null);
                               }
                           }
                );

        return data;

    }

    public MutableLiveData<CardEntity> getLastCardSaved() {
        final MutableLiveData<CardEntity> data = new MutableLiveData<>();

        cardDao.getLastCard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<CardEntity>() {
                               @Override
                               public void onSuccess(CardEntity cardEntity) {
                                   data.setValue(cardEntity);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   data.setValue(null);
                               }
                           }
                );

        return data;
    }


    /**
     * Insert done and Success will return liveData true and revert
     *
     * @param cardEntity
     * @return
     */
    public LiveData<Boolean> insertNewCardIntoDB(CardEntity cardEntity) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        Completable.fromAction(() -> cardDao.insertAll(cardEntity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        data.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        data.setValue(false);
                    }
                });

        return data;
    }

    /**
     * Delete done and Success will return liveData true and revert
     *
     * @param cardEntity
     * @return
     */
    public LiveData<Boolean> deleteCard(final CardEntity cardEntity) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        Completable.fromAction(() -> cardDao.delete(cardEntity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        data.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        data.setValue(false);
                    }
                });

        return data;
    }


    /**
     * Update done and Success will return liveData true and revert
     *
     * @param cardEntity
     * @return
     */
    public LiveData<Boolean> updateCard(final CardEntity cardEntity) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        Completable.fromAction(() -> cardDao.update(cardEntity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        data.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        data.setValue(false);
                    }
                });

        return data;
    }
}
