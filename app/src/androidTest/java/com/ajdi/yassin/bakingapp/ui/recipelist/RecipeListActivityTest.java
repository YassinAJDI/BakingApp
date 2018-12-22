package com.ajdi.yassin.bakingapp.ui.recipelist;

import com.ajdi.yassin.bakingapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void changeText_sameActivity() {
//        onView(withId(R.id.rv_recipe_list)).check(matches(hasMinimumChildCount(MIN_RECIPE_COUNT)));
//        EspressoIdlingResource.
        onView(withId(R.id.rv_recipe_list)).perform(actionOnItemAtPosition(SECOND_POSITION, click()));
        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
//        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
//        Espresso.onView(withId(R.id.textToBeChanged))
//                .check(matches(withText(mStringToBetyped)));
    }
}