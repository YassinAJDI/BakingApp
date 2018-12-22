package com.ajdi.yassin.bakingapp.ui.recipelist;

import android.view.View;

import com.ajdi.yassin.bakingapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author Yassin Ajdi
 * @since 12/21/2018.
 */
@RunWith(JUnit4.class)
@LargeTest
public class RecipeListActivityTest {

    private static final String RECIPE_NAME = "Brownies";
    private static final int MIN_RECIPE_COUNT = 4;

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(
                mActivityRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void onLaunch_recipeListIsDisplayed() {
        // Check that the Recycler View is  displayed
        onView(withId(R.id.rv_recipe_list)).check(matches(isDisplayed()));
    }

    @Test
    public void onLaunch_recipeListHasMinimumFourRecipe() {
        // check recyclerview has enough recipes
        onView(withId(R.id.rv_recipe_list)).check(matches(hasMinimumChildCount(MIN_RECIPE_COUNT)));
    }

    @Test
    public void clickRecipeListItem_openRecipeDetailsActivity() {

        onView(withId(R.id.rv_recipe_list))
                .perform(actionOnItemAtPosition(1, click()));

        onView(withId(R.id.rv_ingredients))
                .check(matches(isDisplayed()));
    }

    @Test
    public void performClicksOnTwoRecipeItems_thenClickBack() {

        onView(withId(R.id.rv_recipe_list)).perform(
                actionOnItemAtPosition(0, click()));
        Espresso.pressBack();

        onView(withId(R.id.rv_recipe_list)).perform(
                actionOnItemAtPosition(1, click()));
        Espresso.pressBack();
    }

    @Test
    public void clickRecipe_checkToolbarTitle() {
        onView(withId(R.id.rv_recipe_list)).perform(actionOnItemAtPosition(1, click()));
        onView(isAssignableFrom(Toolbar.class)).check(matches(getToolbarTitle(is(RECIPE_NAME))));
    }

    private Matcher<? super View> getToolbarTitle(final Matcher<String> stringMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("Toolbar title: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(Toolbar item) {
                return stringMatcher.matches(item.getTitle());
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(
                mActivityRule.getActivity().getCountingIdlingResource());
    }
}