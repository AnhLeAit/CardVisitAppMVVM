package com.anhle.app.cardvisit.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anhle.app.cardvisit.R;
import com.anhle.app.cardvisit.databinding.ActivityMainCardListBinding;
import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.service.model.CardFoo;
import com.anhle.app.cardvisit.service.rest.ApiResponse;
import com.anhle.app.cardvisit.utils.AppUtils;
import com.anhle.app.cardvisit.view.adapter.CardAdapter;
import com.anhle.app.cardvisit.view.base.BaseActivity;
import com.anhle.app.cardvisit.view.callback.CardClickCallback;
import com.anhle.app.cardvisit.view.custom.HeaderDecoration;
import com.anhle.app.cardvisit.view.custom.OnTextChangeListener;
import com.anhle.app.cardvisit.viewmodel.CardListViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;

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
public class CardListMainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = CardListMainActivity.class.getSimpleName();

    public static final int REQUEST_ADD_NEW_CARD = 1;

    private CardListViewModel viewModel;
    private ActivityMainCardListBinding binding;
    private CardAdapter cardAdapter;
    private LinearLayoutManager cardListLayoutManager;

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_card_list);

        // Setup ViewModel
        setupViewModel();

        // Setup cards list
        setupCardList();

        // Setup Fab
        setupFab();

        // Setup Search bar
        setupSearchBar();
    }

    private void scrollToTop() {
        if (cardAdapter != null && cardAdapter.getItemCount() > 0) {
            binding.cardList.scrollToPosition(0);
        }
    }

    private void setupViewModel() {
        AndroidInjection.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CardListViewModel.class);

        // Update Card list here
        viewModel.getAdapterCardsDataObservable().observe(this, (cardList) -> {
            cardAdapter.setCardList(cardList);

            // Scroll to top when list load for the first time
            if (viewModel.isFirstPage()) {
                scrollToTop();
            }
        });

        // Observe all saved Card inside DB was loaded
        viewModel.getAllCardSavedObservable().observe(this, (allCardEntities) -> {
            // Request show the list of CardEntity on top of list
            if (allCardEntities != null) {
                viewModel.showCardEntities(allCardEntities);
            }
        });

        // Observe to handle response card list from API ../cards
        viewModel.getCardsNoPagingObservable().observe(this, (apiResponse) -> {
            processCardsNoPagingResponse(apiResponse);
        });

        // Observe to handle response card list from API ../cards/<page_index>
        viewModel.getCardsPagingObservable().observe(this, (apiResponse) -> {
            processCardsPagingResponse(apiResponse);
        });

        // Observe new Card added inside DB
        viewModel.getLastCardSavedObservable().observe(this, (cardEntity) -> {
            if (cardEntity != null) {
                viewModel.pushNewCardEntityOnTop(cardEntity);
                scrollToTop();
            }
        });
    }

    private BehaviorSubject<String> searchTextChangeStream = BehaviorSubject.create();
    private void setupSearchBar() {

        binding.etSearch.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onNewText(String newText) {
                searchTextChangeStream.onNext(newText == null ? "" : newText);
            }
        });

        // Schedule search trigger to reduce search request.
        searchTextChangeStream.debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String newText) {
                        syncFilter(newText);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    synchronized void syncFilter(String keyword) {
        if ("".equals(keyword) == false) {
            if (viewModel.isSearching() == false) {
                viewModel.beginSearch();
            }
            viewModel.searchFilter(keyword);
        } else {
            if (viewModel.isSearching()) {
                viewModel.endSearch();
                scrollToTop();
            }
        }
    }

    private void setupCardList() {
        cardListLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.cardList.setLayoutManager(cardListLayoutManager);
        cardAdapter = new CardAdapter(cardClickCallback);
        binding.cardList.setAdapter(cardAdapter);
        binding.cardList.addItemDecoration(new HeaderDecoration(getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin)));
        binding.cardList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                // Check for Lazy load next page.
                if (!viewModel.getIsCardsPagingLoading()) {
                    if (cardListLayoutManager.findLastVisibleItemPosition() == cardAdapter.getItemCount() - 1) {
                        if (viewModel.isCanLoadNextPage()) {
                            viewModel.loadNextCardsPaging();
                        }
                    }
                }
            }
        });

        binding.swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void setupFab() {
        binding.fab.setOnClickListener((view) -> {
            Intent addNewCardIntent = new Intent(CardListMainActivity.this, AddNewCardActivity.class);
            startActivityForResult(addNewCardIntent, REQUEST_ADD_NEW_CARD);
        });
    }

    // Append list of Card after REST-ful API /cards was done
    private void processCardsNoPagingResponse(ApiResponse<List<CardFoo>> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                break;

            case SUCCESS:
                viewModel.showCardFoos(apiResponse.data);
                break;

            case ERROR:
                // stop refresh loading
                binding.swipeRefreshLayout.setRefreshing(false);
                if (!AppUtils.isHasInternetConnection(this)) {
                    Toast.makeText(this, getString(R.string.err_msg_no_internet_connection), Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, apiResponse.error.toString());
                break;
        }
    }

    // Append list of Card after REST-ful API /card/<page_index> was done
    private void processCardsPagingResponse(ApiResponse<List<Card>> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                break;

            case SUCCESS:
                if (binding.swipeRefreshLayout.isRefreshing()) {
                    // stop refresh loading
                    binding.swipeRefreshLayout.setRefreshing(false);
                    viewModel.refreshCardsPaging(apiResponse.data);
                } else {
                    // append list
                    viewModel.showNewCardsPagingPage(apiResponse.data);
                }

                viewModel.markEndCardsPagingLoading();


                // Load next page for search
                if (viewModel.isSearching() && viewModel.isCanLoadNextPage() && viewModel.getIsCardsPagingLoading() == false) {
                    viewModel.loadNextCardsPaging();
                }

                break;

            case ERROR:
                // stop refresh loading
                viewModel.markEndCardsPagingLoading();
                binding.swipeRefreshLayout.setRefreshing(false);
                if (!AppUtils.isHasInternetConnection(this)) {
                    Toast.makeText(this, getString(R.string.err_msg_no_internet_connection), Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, apiResponse.error.toString());
                break;
        }
    }

    private final CardClickCallback cardClickCallback = card -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            openCardDetails(card);
        }
    };

    @Override
    public void onRefresh() {
        viewModel.refreshCardsPaging();
        viewModel.refreshCardsNoPaging();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_NEW_CARD) {
            if (resultCode == RESULT_OK && data.getBooleanExtra(AddNewCardActivity.HAS_NEW_CARD, false) == true) {
                viewModel.loadLastCardSaved();
                Log.d(TAG, "onActivityResult --> REQUEST_ADD_NEW_CARD | RESULT_OK | HAS_NEW_CARD --> loadLastCardSaved()");
            }
        }
    }

    /**
     * Shows the card details
     */
    public void openCardDetails(Card card) {
        Intent cardDetailsIntent = new Intent(this, CardDetailsActivity.class);
        cardDetailsIntent.putExtra(CardDetailsActivity.KEY_CARD_MODEL, card);
        startActivity(cardDetailsIntent);
    }

    @Override
    public void onBackPressed() {
        // Press back to clear text search.
        if (viewModel.isSearching()) {
            binding.etSearch.setText("");
        } else {
            super.onBackPressed();
        }
    }
}
