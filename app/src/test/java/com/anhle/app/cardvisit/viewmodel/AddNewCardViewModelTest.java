package com.anhle.app.cardvisit.viewmodel;

import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.repository.CardVisitRepository;
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
public class AddNewCardViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    CardVisitRepository mMockCardVisitRepository;

    private AddNewCardViewModel mViewModel;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void setup() throws Exception {
        // Mock createAnEmptyCard()
        MutableLiveData<Card> emptyCardLiveData = new MutableLiveData<Card>();
        emptyCardLiveData.setValue(new Card());
        Mockito.when(mMockCardVisitRepository.createAnEmptyCard()).thenReturn(emptyCardLiveData);

        // Create ViewMode with a mock CardVisitRepository
        mViewModel = new AddNewCardViewModel(mMockCardVisitRepository);
    }

    @After
    public void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }

    @Test
    public void testValidateCard() {
        LiveData<Boolean> observer = mViewModel.getCardValidObserver();

        // Test Valid card
        Card cardValid = new Card();
        cardValid.setName("Name");
        cardValid.setAddress("Address");
        cardValid.setPosition("Position");
        cardValid.setGender("Male");
        mViewModel.updateCard(cardValid);
        TestObserver.test(observer).assertValue(true);

        // Test invalid card
        Card cardInvalid = new Card();
        // card has no field name so it invalid
        cardInvalid.setAddress("Address");
        cardInvalid.setPosition("Position");
        cardInvalid.setGender("Male");
        mViewModel.updateCard(cardInvalid);
        TestObserver.test(observer).assertValue(false);
    }

    @Test
    public void testSaveCardIntoDB() {
        // Prepare mockCardEntity to save
        MutableLiveData<Boolean> mockSaveSuccess = new MutableLiveData<Boolean>();
        mockSaveSuccess.setValue(true);
        Mockito.when(mMockCardVisitRepository.insertNewCardIntoDB(Mockito.any(CardEntity.class)))
                .thenReturn(mockSaveSuccess);

        // Test save success (CardEntity must have guid for save to Room DB)
        CardEntity c1 = CardEntity.mapToEntity(new Card());
        c1.guid = StringUtils.generateGUID();
        LiveData<Boolean> saveSuccessObserver = mViewModel.saveCardIntoDB(c1);
        TestObserver.test(saveSuccessObserver).assertValue(true);

        // Test save fail when has no guid
        CardEntity c2 = CardEntity.mapToEntity(new Card());
        LiveData<Boolean> saveFailObserver = mViewModel.saveCardIntoDB(c2);
        TestObserver.test(saveFailObserver).assertValue(false);
    }
}