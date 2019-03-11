package com.anhle.app.cardvisit.viewmodel;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.CardFoo;
import com.anhle.app.cardvisit.service.repository.CardVisitRepository;
import com.anhle.app.cardvisit.service.rest.ApiResponse;
import com.anhle.app.cardvisit.service.rest.RestError;
import com.anhle.app.cardvisit.service.room.CardEntity;
import com.anhle.app.cardvisit.utils.AppConstants;
import com.anhle.app.cardvisit.utils.ModelUtils;
import com.anhle.app.cardvisit.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
public class CardListViewModel extends ViewModel {

    private static final int PAGE_SIZE = 50;

    // Repository which will help load data from various source.
    private CardVisitRepository cardVisitRepository;

    // This LiveData just calling and notify one time for load CardEntities
    private final LiveData<List<CardEntity>> allCardSavedObservable;

    // MutableLiveData using when LiveData changes multiple times inside
    // the View has Injected this ViewModel
    private LiveData<CardEntity> lastCardSaved;
    private MutableLiveData<Long> loadLastCardSavedTrigger = new MutableLiveData<>();

    // Use to save data response form api /cards
    private LiveData<ApiResponse<List<CardFoo>>> cardsNoPagingObservable;
    private MutableLiveData<Long> loadCardsNoPagingTrigger = new MutableLiveData<>();

    // Use to save data response form api /cards/<page_index>
    private LiveData<ApiResponse<List<Card>>> cardsPagingObservable;
    private MutableLiveData<Integer> loadCardsPaddingTrigger = new MutableLiveData<>();

    // This MutableLiveData will represent last data show in RecyclerView
    private MutableLiveData<List<Card>> adapterCardsData = new MutableLiveData<>();


    @Inject
    public CardListViewModel(@NonNull CardVisitRepository cardVisitRepository) {
        this.cardVisitRepository = cardVisitRepository;

        // Load all CardEntity saved from Room DB
        allCardSavedObservable = this.cardVisitRepository.getAllCardSaved();

        // Transformations help trigger load last card save by setValue for loadLastCardSavedTrigger.
        lastCardSaved = Transformations.switchMap(loadLastCardSavedTrigger,
                timestamp -> this.cardVisitRepository.getLastCardSaved());

        // Load CardFoo from the API ../cards
        cardsNoPagingObservable = Transformations.switchMap(loadCardsNoPagingTrigger,
                                    timestamp -> this.cardVisitRepository.getCardsNoPaging());
        loadCardsNoPagingTrigger.setValue(System.currentTimeMillis());

        // Notify New page of Card loaded after every time the loadCardsPaddingTrigger make changed.
        cardsPagingObservable = Transformations.switchMap(loadCardsPaddingTrigger, pageIndex -> {
            if (pageIndex < AppConstants.MIN_PAGE_INDEX || pageIndex > AppConstants.MAX_PAGE_INDEX) {
                final MutableLiveData<ApiResponse<List<Card>>> data = new MutableLiveData<>();
                data.setValue(ApiResponse.error(new RestError(0, String.format("Wrong pageIndex: %d, The IndexOutOfBound!", pageIndex))));
                return data;
            }
            return this.cardVisitRepository.getCardsPaging(pageIndex);
        });

        // Load first page of the API ../cards/<page_index>
        loadCardsPaddingTrigger.setValue(AppConstants.MIN_PAGE_INDEX);

    }

    // Adapter update UI must subscribe this
    public LiveData<List<Card>> getAdapterCardsDataObservable() {
        return adapterCardsData;
    }

    /**
     * Expose the LiveData Cards query so the UI can observe it.
     */
    public LiveData<List<CardEntity>> getAllCardSavedObservable() {
        return allCardSavedObservable;
    }

    /**
     * @return Observable lastCardSaved - LiveData to make change when
     * request @{loadLastCardSaved()} from Room Database completed
     */
    public LiveData<CardEntity> getLastCardSavedObservable() {
        return lastCardSaved;
    }

    /**
     * Calling Room persistence database to get lastCardSaved
     */
    public void loadLastCardSaved() {
        loadLastCardSavedTrigger.setValue(System.currentTimeMillis());
    }

    /**
     * Calling the REST-ful API /cards to get data no paging here
     *
     */
    public void refreshCardsNoPaging() {
        loadCardsNoPagingTrigger.setValue(System.currentTimeMillis());
    }

    /**
     * @return Observable cardsNoPagingObservable - LiveData to make UI changes when
     * REST-ful API calling inside @{CardListViewModel()} constructor was called.
     */
    public LiveData<ApiResponse<List<CardFoo>>> getCardsNoPagingObservable() {
        return cardsNoPagingObservable;
    }

    public boolean isCanLoadNextPage() {
        return loadCardsPaddingTrigger.getValue() < AppConstants.MAX_PAGE_INDEX;
    }

    public boolean isFirstPage() {
        return loadCardsPaddingTrigger.getValue() == AppConstants.MIN_PAGE_INDEX;
    }

    public void refreshCardsPaging() {
        arrCardPaging.clear();
        loadCardsPaddingTrigger.setValue(AppConstants.MIN_PAGE_INDEX);
    }

    /**
     * Calling the REST-ful API /cards/<page_index> to get data for this pageIndex
     */
    public void loadNextCardsPaging() {
        isCardsPagingLoading.compareAndSet(false, true);
        int curPageIndex = loadCardsPaddingTrigger.getValue();
        loadCardsPaddingTrigger.setValue(curPageIndex + 1);
    }

    /**
     * @return Observable cardsPagingObservable - LiveData to make UI changes when
     * REST-ful API calling inside @{loadNextCardsPaging()} was called
     */
    public LiveData<ApiResponse<List<Card>>> getCardsPagingObservable() {
        return cardsPagingObservable;
    }

    // TRANSFORM DATA LOAD FROM VARIOUS SOURCES TO ONE SINGLE SOURCE OF Card MODEL
    private List<CardEntity> arrCardEntity = new ArrayList<>();
    private List<CardFoo> arrCardFoo = new ArrayList<>();
    private List<Card> arrCardPaging = new ArrayList<>();
    private String curKeyword = null;
    private AtomicBoolean isSearching = new AtomicBoolean(false);
    private AtomicBoolean isCardsPagingLoading = new AtomicBoolean(false);

    public boolean isSearching() {
        return isSearching.get();
    }

    public boolean getIsCardsPagingLoading() {
        return isCardsPagingLoading.get();
    }
    public void markEndCardsPagingLoading() {
        isCardsPagingLoading.set(false);
    }

    public void notifyUiUpdateToCardList() {
        // Notify UI update Card List
        if (isSearching.get()) {
            searchFilter(curKeyword);
        } else {
            adapterCardsData.setValue(combineCardAdapter());
        }
    }

    private List<Card> combineCardAdapter() {
        List<Card> cards = new ArrayList<>();

        // All list card saved in Room DB on Top of list
        for (CardEntity item : arrCardEntity) {
            cards.add(CardEntity.mapToDTO(item));
        }

        // Append list CardFoo after CardEntities
        for (CardFoo item : arrCardFoo) {
            cards.add(item.convertToCard());
        }

        // Append list Card after CardFoos
        cards.addAll(arrCardPaging);

        return cards;
    }

    /**
     * Filter with keyword and order the showing result in UI card list.
     *
     * @param keyword
     */
    public void searchFilter(String keyword) {
        List<Card> results = new ArrayList<>();
        curKeyword = keyword;
        if (!StringUtils.isEmpty(keyword)) {
            // All list card saved in Room DB on Top of list
            for (CardEntity item : arrCardEntity) {
                if (item.isMatchWith(curKeyword)) {
                    results.add(CardEntity.mapToDTO(item));
                }
            }

            // Append list CardFoo after CardEntities
            for (CardFoo item : arrCardFoo) {
                if (item.isMatchWith(curKeyword)) {
                    results.add(item.convertToCard());
                }
            }

            // Append list Card after CardFoos
            for (Card item : arrCardPaging) {
                if (item.isMatchWith(curKeyword)) {
                    results.add(item);
                }
            }

            adapterCardsData.setValue(results);
        } else {
            adapterCardsData.setValue(combineCardAdapter());
        }
    }

    /**
     * Show list of CardEntity to the UI
     *
     * @param cardEntities
     */
    public void showCardEntities(final List<CardEntity> cardEntities) {
        ModelUtils.injectGuidForCardEntities(cardEntities);
        arrCardEntity.clear();
        arrCardEntity.addAll(cardEntities);
        notifyUiUpdateToCardList();
    }

    /**
     * Bring the last card was added into the top of cards list
     *
     * @param cardEntity
     */
    public void pushNewCardEntityOnTop(final CardEntity cardEntity) {
        if (cardEntity != null) {
            arrCardEntity.add(0, cardEntity);
            notifyUiUpdateToCardList();
        }

    }

    /**
     * Show cards no page from API /cards right below list card entities.
     * Don't care about the order let take combineCardAdapter() and searchFilter()
     * take care of it
     *
     * @param cards
     */
    public void showCardFoos(List<CardFoo> cards) {
        if (cards != null && cards.size() > 0) {
            arrCardFoo.clear();
            arrCardFoo.addAll(cards);
            notifyUiUpdateToCardList();
        }
    }

    /**
     *
     * Don't care about the order let take combineCardAdapter() and searchFilter()
     * take care of it
     *
     * @param cards
     */
    public void showNewCardsPagingPage(List<Card> cards) {
        ModelUtils.injectGuidForCards(cards);
        if (cards != null && cards.size() > 0 && arrCardPaging.size() < loadCardsPaddingTrigger.getValue() * PAGE_SIZE) {
            arrCardPaging.addAll(cards);
            notifyUiUpdateToCardList();
        }
    }

    /**
     * Clear all items type of Card below List card entities
     * then append this cards page into data source again.
     *
     * @param cards
     */
    public void refreshCardsPaging(List<Card> cards) {
        ModelUtils.injectGuidForCards(cards);
        if (cards != null && cards.size() > 0) {
            arrCardPaging.clear();
            arrCardPaging.addAll(cards);
            notifyUiUpdateToCardList();
        }
    }

    public void beginSearch() {
        isSearching.compareAndSet(false, true);
    }

    public void endSearch() {
        curKeyword = null;
        isSearching.compareAndSet(true, false);
        // Notify UI update Card List.
        adapterCardsData.setValue(combineCardAdapter());
    }

    @Override
    protected void onCleared() {
        arrCardEntity.clear();
        arrCardFoo.clear();
        arrCardPaging.clear();
        super.onCleared();
    }

}
