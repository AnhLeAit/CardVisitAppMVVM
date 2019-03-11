package com.anhle.app.cardvisit.di;

import com.anhle.app.cardvisit.view.ui.AddNewCardActivity;
import com.anhle.app.cardvisit.view.ui.CardDetailsActivity;
import com.anhle.app.cardvisit.view.ui.CardListMainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

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
@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract CardListMainActivity contributeCardListMainActivity();

    @ContributesAndroidInjector
    abstract CardDetailsActivity contributeCardDetailsActivity();

    @ContributesAndroidInjector
    abstract AddNewCardActivity contributeAddNewCardActivity();
}