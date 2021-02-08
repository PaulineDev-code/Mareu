package com.openclassrooms.mareu.ui;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static com.openclassrooms.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

@LargeTest
@RunWith(JUnit4.class)
public class FiltersTest {

    private static int ITEMS_COUNT = 1 ;

    @Rule
    public ActivityTestRule<ListReuActivity> mActivityTestRule = new ActivityTestRule<>(ListReuActivity.class);

    @Test
    public void roomFilterTest() {

        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT+4));

        onView(withId(R.id.action_settings)).perform(click());
        onView(withText("Filtrer par salle")).perform(click());
        onView(withText("Salle Luigi")).perform(click());

        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.item_list_room)).check(matches(withText("Luigi")));
    }

    @Test
    public void dateFilterTest() {

        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT+4));

        onView(withId(R.id.action_settings)).perform(click());
        onView(withText("Filtrer par date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.item_list_date)).check(matches(withText("01/01/2021 13h30")));
    }
}
