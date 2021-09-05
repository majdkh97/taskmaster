package com.example.taskmaster;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TaskMasterTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void myTest() {
        Espresso.onView(ViewMatchers.withId(R.id.addtask)).perform(click());
        Espresso.onView(withId(R.id.editText1)).perform(clearText(),typeText("TaskTest"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.editText2)).perform(clearText(),typeText("TaskTest"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void myTest1() {
        Espresso.onView(ViewMatchers.withId(R.id.SettingsActivityBtn)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.editTextUserName)).perform(clearText(),typeText("Majd"));
        Espresso.onView(ViewMatchers.withId(R.id.SaveButtonSettingsPage)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.userNameTextViewMainActivity)).check(matches(withText("Welcome Majd")));
    }

    @Test
    public void myTest2() {
        Espresso.onView(ViewMatchers.withId(R.id.TextViewUserName)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.imageView2)).check(matches(isDisplayed()));
    }

    @Test
    public void myTest3() {
        Espresso.onView(withId(R.id.taskListRecyclerView)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("1234")),click()));
    }
}
