package com.anhle.app.cardvisit.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anhle.app.cardvisit.R;
import com.anhle.app.cardvisit.databinding.CardListItemBinding;
import com.anhle.app.cardvisit.service.model.Card;
import com.anhle.app.cardvisit.view.callback.CardClickCallback;
import com.anhle.app.cardvisit.view.custom.OnOneClickListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

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
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    public final static String TAG = CardAdapter.class.getName();
    
    private List<Card> cardList = null;

    @Nullable
    private final CardClickCallback cardClickCallback;

    public CardAdapter(@Nullable CardClickCallback cardClickCallback) {
        this.cardClickCallback = cardClickCallback;
    }

    public void setCardList(final List<Card> newCardList) {
        if (cardList == null) {
            cardList = newCardList;
            notifyItemRangeInserted(0, newCardList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {

                @Override
                public int getOldListSize() {
                    return cardList.size();
                }

                @Override
                public int getNewListSize() {
                    return newCardList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    String oldGuid = "";
                    String newGuid = "";

                    if (oldItemPosition < cardList.size()) {
                        oldGuid = cardList.get(oldItemPosition).getGuid();
                    }

                    if (newItemPosition < newCardList.size()) {
                        newGuid = newCardList.get(newItemPosition).getGuid();
                    }

                    return oldGuid.equals(newGuid);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    String oldGuid = "";
                    String newGuid = "";

                    if (oldItemPosition < cardList.size()) {
                        oldGuid = cardList.get(oldItemPosition).getGuid();
                    }

                    if (newItemPosition < newCardList.size()) {
                        newGuid = newCardList.get(newItemPosition).getGuid();
                    }

                    return oldGuid.equals(newGuid);
                }
            });

            cardList = newCardList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.card_list_item,
                        parent, false);

        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card data = cardList.get(position);
        if (data != null) {
            holder.binding.setCard(data);
            holder.binding.executePendingBindings();
            holder.binding.cardItem.setOnClickListener(new OnOneClickListener() {
                @Override
                public void onOneClick(View v) {
                    if (cardClickCallback != null) {
                        cardClickCallback.onClick(holder.binding.getCard());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (cardList == null) {
            return 0;
        }
        return cardList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        final CardListItemBinding binding;

        public CardViewHolder(CardListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
