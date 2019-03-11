package com.anhle.app.cardvisit.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.anhle.app.cardvisit.R;
import com.anhle.app.cardvisit.databinding.ActivityAddNewCardBinding;
import com.anhle.app.cardvisit.service.room.CardEntity;
import com.anhle.app.cardvisit.utils.StringUtils;
import com.anhle.app.cardvisit.view.base.BaseActivity;
import com.anhle.app.cardvisit.view.custom.OnOneClickListener;
import com.anhle.app.cardvisit.view.custom.OnTextChangeListener;
import com.anhle.app.cardvisit.viewmodel.AddNewCardViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

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
public class AddNewCardActivity extends BaseActivity {

    public static final String TAG = AddNewCardActivity.class.getSimpleName();
    public static final String HAS_NEW_CARD = "HAS_NEW_CARD";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private AddNewCardViewModel viewModel;

    private ActivityAddNewCardBinding binding;

    // For select gender
    private ListPopupWindow gendersPopup;
    private String[] arrGender;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_card);
        binding.toolbar.setNavigationOnClickListener((view) -> onBackPressed());

        setupSaveButton();
        setupChooseGender();
        setupChooseDOB();
        setupViewModel();
    }

    @Override
    public void onBackPressed() {
        hideKeyboard(binding.etAbout);
        hideKeyboard(binding.etAddress);
        hideKeyboard(binding.etDisplayName);
        hideKeyboard(binding.etPosition);
        super.onBackPressed();
    }

    private void setupViewModel() {
        AndroidInjection.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddNewCardViewModel.class);

        // Observe card validator for enable/disable saveButton
        viewModel.getCardValidObserver().observe(this, (isCardValid) -> {
            binding.btnSave.setEnabled(isCardValid);
            if (isCardValid && isKeyboardShowing()) {
                binding.btnActionSave.setVisibility(View.VISIBLE);
            } else {
                binding.btnActionSave.setVisibility(View.GONE);
            }
        });

        // Observe onTextChangeListener to update card's fields and trigger card validation
        // every time text changed.
        binding.etDisplayName.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onNewText(String newText) {
                viewModel.updateCard(viewModel.getCardValue().setName(newText));
            }
        });
        binding.etAddress.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onNewText(String newText) {
                viewModel.updateCard(viewModel.getCardValue().setAddress(newText));
            }
        });
        binding.etPosition.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onNewText(String newText) {
                viewModel.updateCard(viewModel.getCardValue().setPosition(newText));
            }
        });
        binding.etAbout.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onNewText(String newText) {
                viewModel.updateCard(viewModel.getCardValue().setAbout(newText));
            }
        });
        binding.etGender.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onNewText(String newText) {
                viewModel.updateCard(viewModel.getCardValue().setGender(newText));
            }
        });
        binding.etDob.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onNewText(String newText) {
                viewModel.updateCard(viewModel.getCardValue().setDob(newText));
            }
        });
    }

    private boolean isKeyboardShowing() {
        // TODO: Check the soft keyboard showing right here!
        return false;
    }

    private void setupChooseGender() {
        binding.etGender.setOnClickListener(new OnOneClickListener() {
            @Override
            public void onOneClick(View v) {
                if (gendersPopup.isShowing() == false) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, arrGender);
                    gendersPopup.setAnchorView(v);
                    gendersPopup.setAdapter(adapter);
                    gendersPopup.setWidth(binding.etGender.getWidth());
                    gendersPopup.setOnItemClickListener((parent, view, position, id) -> {
                        gendersPopup.dismiss();
                        binding.etGender.setText(arrGender[position]);
                    });
                    gendersPopup.show();
                } else {
                    gendersPopup.dismiss();
                }
            }
        });

        arrGender = getResources().getStringArray(R.array.genders);
        gendersPopup = new ListPopupWindow(this);
    }

    private void setupChooseDOB() {
        binding.etDob.setOnClickListener(new OnOneClickListener() {
            @Override
            public void onOneClick(View v) {
                showDobDialog();
            }
        });
    }

    private void showDobDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) -> binding.etDob.setText(StringUtils.createDobString(year, monthOfYear, dayOfMonth)),
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Initial day selection
        );

        dpd.show(getSupportFragmentManager(), "DatePickerDialog");
    }

    private void setupSaveButton() {
        binding.btnActionSave.setOnClickListener((view) -> {
            saveNewCardIntoDB();
        });
        binding.btnSave.setOnClickListener((view) -> {
            saveNewCardIntoDB();
        });
    }

    /**
     * Request ViewModel handle logic save
     * <p>
     * - Success: finish back to List card screen.
     * - Fail: Toast short error.
     */
    private void saveNewCardIntoDB() {
        CardEntity newCardEntity = CardEntity.mapToEntity(viewModel.getCardValue());
        newCardEntity.guid = StringUtils.generateGUID();
        viewModel.saveCardIntoDB(newCardEntity).observe(this, (isSuccess) -> {
            if (isSuccess) {
                Intent data = new Intent();
                data.putExtra(HAS_NEW_CARD, true);
                // No need put any data RESULT_OK is save into DB new card success.
                setResult(RESULT_OK, data);
                finish();
            } else {
                Toast.makeText(this, "Save Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
