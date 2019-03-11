package com.anhle.app.cardvisit.view.ui;

import android.view.View;

import com.anhle.app.cardvisit.R;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.BoundedMatcher;
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
public class CardListMainActivityTest {

    @Rule
    public ActivityTestRule<CardListMainActivity> activityRule =
            new ActivityTestRule<>(CardListMainActivity.class);

    @Test
    public void testSearchBar() {
        // Test search with hint
        Espresso.onView(ViewMatchers.withHint(R.string.textHintSearch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    public class RecyclerViewHasItemAssertion implements ViewAssertion {

        public RecyclerViewHasItemAssertion() {
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            Assert.assertTrue(adapter.getItemCount() > 0);
        }
    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            Assert.assertThat(adapter.getItemCount(), CoreMatchers.is(expectedCount));
        }
    }

    public static Matcher<View> atPositionOnView(final int position, final Matcher<View> itemMatcher,
                                                 @NonNull final int targetViewId) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has view id " + itemMatcher + " at position " + position);
            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                View targetView = viewHolder.itemView.findViewById(targetViewId);
                return itemMatcher.matches(targetView);
            }
        };
    }

    @Test
    public void testOpenCardDetailActivity() {
        Assert.assertNotNull(activityRule.getActivity().findViewById(R.id.card_list));
        RecyclerView rc = activityRule.getActivity().findViewById(R.id.card_list);

        // Make sure list view has item
        if (rc.getAdapter().getItemCount() > 0) {
            Intents.init();
            Espresso.onView(ViewMatchers.withId(R.id.card_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
            Intents.intended(IntentMatchers.hasComponent(CardDetailsActivity.class.getName()));
            Intents.release();
        }
    }

    @Test
    public void testOpenAddNewCardActivity() {
        Intents.init();
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(AddNewCardActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testAddNewCardProperly() {
        // Open Add new card screen.
        Intents.init();

        // Click Add Fab open AddNewCardActivity
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(AddNewCardActivity.class.getName()));

        // Fill info to create new Card
        Espresso.onView(ViewMatchers.withId(R.id.et_display_name)).perform(ViewActions.replaceText("name"));
        Espresso.onView(ViewMatchers.withId(R.id.et_address)).perform(ViewActions.replaceText("address"));
        Espresso.onView(ViewMatchers.withId(R.id.et_position)).perform(ViewActions.replaceText("position"));
        Espresso.onView(ViewMatchers.withId(R.id.et_about)).perform(ViewActions.replaceText("about"));
        Espresso.onView(ViewMatchers.withId(R.id.et_dob)).perform(ViewActions.replaceText("02/12/1992"));
        Espresso.onView(ViewMatchers.withId(R.id.et_gender)).perform(ViewActions.replaceText("Male"));

        // Save button must enable and perform click to Create card and close this Activity
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).perform(ViewActions.click());

        // Check for new card added on the top of RecyclerView.
        Espresso.onView(ViewMatchers.withId(R.id.card_list))
                .check(ViewAssertions.matches(atPositionOnView(0, ViewMatchers.withText("name"), R.id.tv_display_name)));
        Espresso.onView(ViewMatchers.withId(R.id.card_list))
                .check(ViewAssertions.matches(atPositionOnView(0, ViewMatchers.withText("Mobile: null"), R.id.tv_mobile)));
        Espresso.onView(ViewMatchers.withId(R.id.card_list))
                .check(ViewAssertions.matches(atPositionOnView(0, ViewMatchers.withText("Address: address"), R.id.tv_address)));
        Espresso.onView(ViewMatchers.withId(R.id.card_list))
                .check(ViewAssertions.matches(atPositionOnView(0, ViewMatchers.withText("Company: null"), R.id.tv_company)));
        Espresso.onView(ViewMatchers.withId(R.id.card_list))
                .check(ViewAssertions.matches(atPositionOnView(0, ViewMatchers.withText("Position: position"), R.id.tv_position)));

        Intents.release();
    }
}
