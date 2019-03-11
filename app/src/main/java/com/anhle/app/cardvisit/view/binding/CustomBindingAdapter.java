package com.anhle.app.cardvisit.view.binding;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhle.app.cardvisit.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.databinding.BindingAdapter;

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
public class CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("bind:htmlString")
    public static void showHtmlText(TextView textView, String htmlString) {
        textView.setText(Html.fromHtml(htmlString));
    }

    @BindingAdapter("bind:loadImageUrl")
    public static void loadImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    @BindingAdapter("bind:loadImageAndRoundCircle")
    public static void loadImageAndRoundCircle(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.mipmap.ic_avatar_place_holder)
                .into(view);
    }
}