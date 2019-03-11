package com.anhle.app.cardvisit.viewmodel;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.repository.CardVisitRepository;
import com.anhle.app.cardvisit.service.room.CardEntity;
import com.anhle.app.cardvisit.utils.StringUtils;

import javax.inject.Inject;

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
public class AddNewCardViewModel extends ViewModel {

    private static final String TAG = AddNewCardViewModel.class.getName();

    // CardVisitRepository will be inject automatically by Dagger
    // when the constructor func was called
    private CardVisitRepository cardVisitRepository;

    // The live data for create new card visit
    private MutableLiveData<Card> card;

    // MutableLiveData using when LiveData changes multiple times inside
    // the View has Injected this ViewModel
    private final LiveData<Boolean> isCardValid;

    @Inject
    public AddNewCardViewModel(@NonNull CardVisitRepository cardVisitRepository) {
        this.cardVisitRepository = cardVisitRepository;
        this.card = cardVisitRepository.createAnEmptyCard();
        this.isCardValid = Transformations.map(card, (newCard) -> isCardValid(newCard));
    }

    /**
     * Validate and notify change to subscriber
     *
     * @param data Latest Card data
     * @return true if valid can be save
     */
    private boolean isCardValid(Card data) {
        // Check card if it valid to save isValid == true
        boolean isValid = (StringUtils.isEmpty(data.getName()) == false &&
                StringUtils.isEmpty(data.getAddress()) == false &&
                StringUtils.isEmpty(data.getPosition()) == false &&
                StringUtils.isEmpty(data.getGender()) == false);

        return isValid;
    }

    public Card getCardValue() {
        return card.getValue();
    }

    /**
     * Update the card LiveData value
     * this will be notify to the subscribers know that data was changed
     *
     * @param card new Card data.
     */
    public void updateCard(Card card) {
        this.card.setValue(card);
    }

    /**
     * @return The observable isCardValid LiveData and do not allow for make change on it
     */
    public LiveData<Boolean> getCardValidObserver() {
        return isCardValid;
    }

    /**
     * Save card into DB and observe success or not
     *
     * @return Boolean LiveData which observable.
     */
    public LiveData<Boolean> saveCardIntoDB(CardEntity cardEntity) {
        if (cardEntity != null && !StringUtils.isEmpty(cardEntity.guid)) {
            return cardVisitRepository.insertNewCardIntoDB(cardEntity);
        }

        MutableLiveData saveFail = new MutableLiveData<Boolean>();
        saveFail.setValue(false);
        return saveFail;
    }

}
