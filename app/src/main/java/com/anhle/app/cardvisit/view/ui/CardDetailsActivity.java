package com.anhle.app.cardvisit.view.ui;

import android.os.Bundle;
import android.util.Log;

import com.anhle.app.cardvisit.R;
import com.anhle.app.cardvisit.databinding.ActivityCardDetailsBinding;
import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.rest.ApiResponse;
import com.anhle.app.cardvisit.view.base.BaseActivity;
import com.anhle.app.cardvisit.viewmodel.CardDetailsViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;

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
public class CardDetailsActivity extends BaseActivity {

    public static final String TAG = CardDetailsActivity.class.getSimpleName();
    public static final String KEY_CARD_MODEL = "card_model";

    private ActivityCardDetailsBinding binding;
    private CardDetailsViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_details);
        binding.toolbar.setNavigationOnClickListener((view) ->  onBackPressed());

        setupViewModel();

        // Prepare data
        Card card = getIntent().getExtras().getParcelable(KEY_CARD_MODEL);
        if (card != null) {
            // Set liveData for data binding
            viewModel.setCard(card);

            // Load card details from API - LOL
            if (card.getId() != null) {
                viewModel.setCardID(card.getId());
            }
        }

        // Fill data into UI
        binding.setCardDetailsViewModel(viewModel);
    }

    private void setupViewModel() {
        AndroidInjection.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CardDetailsViewModel.class);
        // Observe card data
        viewModel.getObservableCard().observe(this, cardApiResponse -> processResponse(cardApiResponse));
    }

    private void processResponse(ApiResponse<Card> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                break;

            case SUCCESS:
                viewModel.setCard(apiResponse.data);
                break;

            case ERROR:
                Log.d(TAG, apiResponse.error.toString());
                break;
        }
    }

}
