package com.anhle.app.cardvisit.di;

import com.anhle.app.cardvisit.viewmodel.AddNewCardViewModel;
import com.anhle.app.cardvisit.viewmodel.CardDetailsViewModel;
import com.anhle.app.cardvisit.viewmodel.CardListViewModel;
import com.anhle.app.cardvisit.viewmodel.CardViewModelFactory;

import dagger.Subcomponent;

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
 *
 * A sub component to create ViewModels. It is called by the
 * {@link CardViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    CardListViewModel cardListViewModel();
    CardDetailsViewModel cardDetailsViewModel();
    AddNewCardViewModel addNewCardViewModel();

}
