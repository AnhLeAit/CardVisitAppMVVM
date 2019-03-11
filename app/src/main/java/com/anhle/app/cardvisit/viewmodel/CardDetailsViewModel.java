package com.anhle.app.cardvisit.viewmodel;

import android.util.Log;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.repository.CardVisitRepository;
import com.anhle.app.cardvisit.service.rest.ApiResponse;

import javax.inject.Inject;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import io.reactivex.annotations.NonNull;

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
public class CardDetailsViewModel extends ViewModel {

    private static final String TAG = CardDetailsViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    // CardVisitRepository will be inject automatically by Dagger
    // when the constructor func was called
    private CardVisitRepository cardVisitRepository;

    // This LiveData just calling and notify one time
    private final LiveData<ApiResponse<Card>> cardObservable;

    private final MutableLiveData<Long> cardID;

    public ObservableField<Card> card = new ObservableField<>();

    @Inject
    public CardDetailsViewModel(@NonNull CardVisitRepository cardVisitRepository) {
        this.cardVisitRepository = cardVisitRepository;
        this.cardID = new MutableLiveData<>();

        cardObservable = Transformations.switchMap(cardID, input -> {
            if (input == null) {
                Log.i(TAG, "CardDetailsViewModel cardID is absent!!!");
                return ABSENT;
            }

            Log.i(TAG,"CardDetailsViewModel cardID is " + cardID.getValue());

            /**
             * API URL does not exist so always return an ApiResponse.error
             * and I ignore it by print log only.
             *
             *      UI will be binding using the Parcelable Object I have already
             *      put inside the activity's Intent.
            */
            return this.cardVisitRepository.getCardDetails(cardID.getValue());
        });
    }

    public LiveData<ApiResponse<Card>> getObservableCard() {
        return cardObservable;
    }

    public void setCard(Card card) {
        this.card.set(card);
    }

    public void setCardID(long cardID) {
        this.cardID.setValue(cardID);
    }
}
