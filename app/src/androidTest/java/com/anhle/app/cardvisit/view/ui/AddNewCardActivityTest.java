package com.anhle.app.cardvisit.view.ui;

import com.anhle.app.cardvisit.R;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

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
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddNewCardActivityTest {

    @Rule
    public ActivityTestRule<AddNewCardActivity> activityRule =
            new ActivityTestRule<>(AddNewCardActivity.class);

    @Test
    public void testEnableSaveButton() {
        Espresso.onView(ViewMatchers.withId(R.id.et_display_name)).perform(ViewActions.replaceText("name"));
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));

        Espresso.onView(ViewMatchers.withId(R.id.et_address)).perform(ViewActions.replaceText("address"));
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));

        Espresso.onView(ViewMatchers.withId(R.id.et_position)).perform(ViewActions.replaceText("position"));
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));

        Espresso.onView(ViewMatchers.withId(R.id.et_about)).perform(ViewActions.replaceText("about"));
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));

        Espresso.onView(ViewMatchers.withId(R.id.et_dob)).perform(ViewActions.replaceText("02/12/1992"));
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));

        Espresso.onView(ViewMatchers.withId(R.id.et_gender)).perform(ViewActions.replaceText("Male"));
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).check(ViewAssertions.matches(ViewMatchers.isEnabled()));

    }
}