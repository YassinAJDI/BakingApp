package com.ajdi.yassin.bakingapp.ui.recipelist;

import com.ajdi.yassin.bakingapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author Yassin Ajdi
 * @since 12/21/2018.
 */
@RunWith(JUnit4.class)
@LargeTest
public class RecipeListActivityTest {

    private static final int MIN_RECIPE_COUNT = 4;
    private static final int SECOND_POSITION = 1;

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    @Before
    public void setUp() throws Exception {
        Espresso.registerIdlingResources(
                mActivityRule.getActivity().getCountingIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        Espresso.unregisterIdlingResources(
                mActivityRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void onRecipeListActivityOpen_displayRecyclerView() {
        // Check that the Recycler View is  displayed
        onView(withId(R.id.rv_recipe_list)).check(matches(isDisplayed()));
    }

    @Test
    public void changeText_sameActivity() {
        // check recyclerview has enough recipes
        onView(withId(R.id.rv_recipe_list)).check(matches(hasMinimumChildCount(MIN_RECIPE_COUNT)));

//        onView(withId(R.id.rv_recipe_list)).perform(actionOnItemAtPosition(SECOND_POSITION, click()));
    }

    @Test
    public void clickRecipeListItem_openRecipeDetailsActivity() {

        onView(withId(R.id.rv_recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.rv_ingredients))
                .check(matches(isDisplayed()));
    }
}