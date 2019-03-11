package com.anhle.app.cardvisit.viewmodel;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.CardFoo;
import com.anhle.app.cardvisit.service.repository.CardVisitRepository;
import com.anhle.app.cardvisit.service.rest.ApiResponse;
import com.anhle.app.cardvisit.service.room.CardEntity;
import com.anhle.app.cardvisit.utils.StringUtils;
import com.jraska.livedata.TestObserver;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

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
@RunWith(MockitoJUnitRunner.class)
public class CardListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    CardVisitRepository mMockCardVisitRepository;

    private CardListViewModel mViewModel;
    private MutableLiveData<List<CardEntity>> mockLiveCardEntities;
    private MutableLiveData<CardEntity> mockLiveLastCardSaved;
    private MutableLiveData<ApiResponse<List<CardFoo>>> mockLiveCardFoos;
    private MutableLiveData<ApiResponse<List<Card>>> mockLiveCardsPage1;
    private MutableLiveData<ApiResponse<List<Card>>> mockLiveCardsPage2;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void setup() throws Exception {
        // Mock createAnEmptyCard()
        MutableLiveData<Card> emptyCardLiveData = new MutableLiveData<Card>();
        emptyCardLiveData.setValue(new Card());

        // Mock list of CardEntity by Room DB via CardVisitRepository
        mockLiveCardEntities = createMockLiveCardEntities();
        Mockito.when(mMockCardVisitRepository.getAllCardSaved()).thenReturn(mockLiveCardEntities);

        // Mock last card CardEntity saved by Room DB via CardVisitRepository
        mockLiveLastCardSaved = createMockLiveLastCardSaved();

        // Mock list of CardFoo load by api ../cards via CardVisitRepository
        mockLiveCardFoos = createMockLiveCardFoos();

        // Mock list of Card load by api ../cards/<page_index> via CardVisitRepository
        mockLiveCardsPage1 = createMockLiveCardPaging(1);
        mockLiveCardsPage2 = createMockLiveCardPaging(2);

        // Create ViewMode with a mock CardVisitRepository
        mViewModel = new CardListViewModel(mMockCardVisitRepository);
    }

    @After
    public void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }

    // Every Test will be check the LiveData flow
    // do something and expect adapterCardsData update with correct
    // data to show UI which end user can see.

    private MutableLiveData<List<CardEntity>> createMockLiveCardEntities() {
        List<CardEntity> mockCardEntities = new ArrayList<>();
        CardEntity mockCardEntity = new CardEntity();
        mockCardEntity.name = "mockCardEntity";
        mockCardEntity.guid = StringUtils.generateGUID();
        mockCardEntities.add(mockCardEntity);
        MutableLiveData<List<CardEntity>> mockLiveCardEntities = new MutableLiveData<>();
        mockLiveCardEntities.setValue(mockCardEntities);
        return mockLiveCardEntities;
    }

    private MutableLiveData<CardEntity> createMockLiveLastCardSaved() {
        CardEntity mockLastCardSaved = new CardEntity();
        mockLastCardSaved.name = "mockLastCardSaved";
        mockLastCardSaved.guid = StringUtils.generateGUID();
        MutableLiveData<CardEntity> mockLiveLastCardSaved = new MutableLiveData<>();
        mockLiveLastCardSaved.setValue(mockLastCardSaved);
        return mockLiveLastCardSaved;
    }

    private MutableLiveData<ApiResponse<List<CardFoo>>> createMockLiveCardFoos() {
        List<CardFoo> mockCardFoos = new ArrayList<>();
        CardFoo mockCardFoo = new CardFoo();
        mockCardFoo.setName("mockCardFoo");
        mockCardFoo.setGuid(StringUtils.generateGUID());
        mockCardFoos.add(mockCardFoo);
        MutableLiveData mockLiveCardFoos = new MutableLiveData<>();
        mockLiveCardFoos.setValue(ApiResponse.success(mockCardFoos, null));
        return mockLiveCardFoos;
    }

    private MutableLiveData<ApiResponse<List<Card>>> createMockLiveCardPaging(int pageIndex) {
        List<Card> mockCardPaging = new ArrayList<>();
        Card mockCard = new Card();
        mockCard.setName("mockCardPaging_page:" + pageIndex);
        mockCard.setGuid(StringUtils.generateGUID());
        mockCardPaging.add(mockCard);
        MutableLiveData mockLiveCardPaging = new MutableLiveData<>();
        mockLiveCardPaging.setValue(ApiResponse.success(mockCardPaging, null));
        return mockLiveCardPaging;
    }

    private List<Card> convertListCardEntities2ListCards(List<CardEntity> cardEntities) {
        if (cardEntities != null) {
            List<Card> cards = new ArrayList<>();
            for (CardEntity ce : cardEntities) {
                cards.add(CardEntity.mapToDTO(ce));
            }
            return cards;
        }
        return null;
    }

    private List<Card> convertListCardFoos2ListCards(List<CardFoo> cardFoos) {
        if (cardFoos != null) {
            List<Card> cards = new ArrayList<>();
            for (CardFoo cf : cardFoos) {
                cards.add(cf.convertToCard());
            }
            return cards;
        }
        return null;
    }

    private boolean isTwoListCardHaveSameValue(List<Card> cards, List<Card> expectExposeCards) {
        if (cards != null && expectExposeCards != null) {
            if (expectExposeCards.size() == cards.size()) {
                for (int i = 0; i < expectExposeCards.size(); i++) {
                    if (cards.get(i).getGuid() == null) {
                        return false;
                    } else if (cards.get(i).getGuid().equals(expectExposeCards.get(i).getGuid()) == false) {
                        return false;
                    }
                }
                return true;
            }
        } else if (cards == null && expectExposeCards == null) {
            return true;
        }

        return false;
    }


    @Test
    public void testExposeAllCardEntitiesSaved() {
        LiveData<List<Card>> uiExposeObserver = mViewModel.getAdapterCardsDataObservable();

        // Do test check the Data show in adapterCardsData must be the same.
        mViewModel.showCardEntities(mockLiveCardEntities.getValue());
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    // Set expect expose cards after show card entities which mack saved in Room DB
                    List<Card> expectExposeCards = convertListCardEntities2ListCards(mockLiveCardEntities.getValue());
                    return isTwoListCardHaveSameValue(cards, expectExposeCards);
                });

    }


    @Test
    public void testPushNewCardEntityOnTop() {
        LiveData<List<Card>> uiExposeObserver = mViewModel.getAdapterCardsDataObservable();

        // show list card saved
        mViewModel.showCardEntities(mockLiveCardEntities.getValue());

        // Do test show push new CardEntity OnTop
        mViewModel.pushNewCardEntityOnTop(mockLiveLastCardSaved.getValue());
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    // Set expect expose cards after show card entities which mack saved in Room DB
                    List<Card> expectExposeCards = convertListCardEntities2ListCards(mockLiveCardEntities.getValue());
                    Card lastCardSaved = CardEntity.mapToDTO(mockLiveLastCardSaved.getValue());
                    expectExposeCards.add(0, lastCardSaved);
                    return isTwoListCardHaveSameValue(cards, expectExposeCards);
                });
    }

    @Test
    public void testExposeAllCardFoos() {
        LiveData<List<Card>> uiExposeObserver = mViewModel.getAdapterCardsDataObservable();

        // Do test check the Data show in adapterCardsData must be the same.
        mViewModel.showCardFoos(mockLiveCardFoos.getValue().data);
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    // Set expect expose cards after show card foos which loaded from API ../cards
                    List<Card> expectExposeCards = convertListCardFoos2ListCards(mockLiveCardFoos.getValue().data);
                    return isTwoListCardHaveSameValue(cards, expectExposeCards);
                });

    }

    @Test
    public void testExposeNewCardPaging() {
        LiveData<List<Card>> uiExposeObserver = mViewModel.getAdapterCardsDataObservable();

        // Test expose first page.
        mViewModel.showNewCardsPagingPage(mockLiveCardsPage1.getValue().data);
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    // Set expect expose cards after show card foos which loaded from API ../cards
                    List<Card> expectExposeCards = mockLiveCardsPage1.getValue().data;
                    return isTwoListCardHaveSameValue(cards, expectExposeCards);
                });

        // Test expose second page.
        mViewModel.showNewCardsPagingPage(mockLiveCardsPage2.getValue().data);
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    // Set expect expose cards after show card foos which loaded from API ../cards
                    List<Card> expectExposeCards = new ArrayList<>();
                    expectExposeCards.addAll(mockLiveCardsPage1.getValue().data);
                    expectExposeCards.addAll(mockLiveCardsPage2.getValue().data);
                    return isTwoListCardHaveSameValue(cards, expectExposeCards);
                });

    }

    @Test
    public void testExposeSearchFilter() {
        LiveData<List<Card>> uiExposeObserver = mViewModel.getAdapterCardsDataObservable();

        // Expose all CardEntities saved in Room DB
        mViewModel.showCardEntities(mockLiveCardEntities.getValue());

        // Expose all CardFoos (load by API ../cards)
        mViewModel.showCardFoos(mockLiveCardFoos.getValue().data);

        // Expose first page of Cards (load by API ../cards/page_index)
        mViewModel.showNewCardsPagingPage(mockLiveCardsPage1.getValue().data);

        // Expose second page of Cards (load by API ../cards/page_index)
        mViewModel.showNewCardsPagingPage(mockLiveCardsPage2.getValue().data);

        // Expose a new CardEntity saved in Room DB
        mViewModel.pushNewCardEntityOnTop(mockLiveLastCardSaved.getValue());

        mViewModel.beginSearch();

        // Doing test search with keyword is 'mock'. Expect show all because
        // all mock Card have name suffix with 'mock'
        mViewModel.searchFilter("mock");
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    // Set expect expose cards after show card foos which loaded from API ../cards
                    List<Card> expectExposeCards = new ArrayList<>();
                    expectExposeCards.addAll(convertListCardEntities2ListCards(mockLiveCardEntities.getValue()));
                    expectExposeCards.addAll(convertListCardFoos2ListCards(mockLiveCardFoos.getValue().data));
                    expectExposeCards.addAll(mockLiveCardsPage1.getValue().data);
                    expectExposeCards.addAll(mockLiveCardsPage2.getValue().data);
                    expectExposeCards.add(0, CardEntity.mapToDTO(mockLiveLastCardSaved.getValue()));
                    return isTwoListCardHaveSameValue(cards, expectExposeCards);
                });

        // Doing test search with keyword is 'unitTest'. Expect result is empty because
        // it doesn't match any Card.
        mViewModel.searchFilter("unitTest");
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    return cards.size() == 0;
                });

        // Test return full list after search end
        mViewModel.endSearch();
        TestObserver.test(uiExposeObserver)
                .assertHasValue()
                .assertHistorySize(1)
                .assertValue(cards -> { // return list of Card must match with condition below.
                    // Set expect expose cards after show card foos which loaded from API ../cards
                    List<Card> expectExposeCards = new ArrayList<>();
                    expectExposeCards.addAll(convertListCardEntities2ListCards(mockLiveCardEntities.getValue()));
                    expectExposeCards.addAll(convertListCardFoos2ListCards(mockLiveCardFoos.getValue().data));
                    expectExposeCards.addAll(mockLiveCardsPage1.getValue().data);
                    expectExposeCards.addAll(mockLiveCardsPage2.getValue().data);
                    expectExposeCards.add(0, CardEntity.mapToDTO(mockLiveLastCardSaved.getValue()));
                    return isTwoListCardHaveSameValue(cards, expectExposeCards);
                });

    }

}