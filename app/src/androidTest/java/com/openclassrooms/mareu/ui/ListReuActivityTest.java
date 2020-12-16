package com.openclassrooms.mareu.ui;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.mareu.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListReuActivityTest {

    @Rule
    public ActivityTestRule<ListReuActivity> mActivityTestRule = new ActivityTestRule<>(ListReuActivity.class);

    @Test
    public void listReuActivityTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.item_list_name), withText("DummyReunion2"),
                        withParent(withParent(withId(R.id.recyclerview))),
                        isDisplayed()));
        textView.check(matches(withText("DummyReunion2")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_list_date), withText("31/12/2020 13h00"),
                        withParent(withParent(withId(R.id.recyclerview))),
                        isDisplayed()));
        textView2.check(matches(withText("31/12/2020 13h00")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_list_room), withText("Luigi"),
                        withParent(withParent(withId(R.id.recyclerview))),
                        isDisplayed()));
        textView3.check(matches(withText("Luigi")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.item_list_emails), withText("parcipant1@gmail.com, particip"),
                        withParent(withParent(withId(R.id.recyclerview))),
                        isDisplayed()));
        textView4.check(matches(withText("parcipant1@gmail.com, particip")));
    }
}
