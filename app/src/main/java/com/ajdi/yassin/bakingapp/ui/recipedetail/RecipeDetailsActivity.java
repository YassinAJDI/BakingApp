package com.ajdi.yassin.bakingapp.ui.recipedetail;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.local.model.Recipe;
import com.ajdi.yassin.bakingapp.data.local.model.Step;
import com.ajdi.yassin.bakingapp.ui.stepdetail.StepDetailActivity;
import com.ajdi.yassin.bakingapp.ui.stepdetail.StepDetailFragment;
import com.ajdi.yassin.bakingapp.utils.ActivityUtils;
import com.ajdi.yassin.bakingapp.utils.Constants;
import com.ajdi.yassin.bakingapp.utils.Injection;
import com.ajdi.yassin.bakingapp.utils.ViewModelFactory;
import com.ajdi.yassin.bakingapp.widget.RecipeWidgetProvider;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class RecipeDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_DATA = "extra_recipe";

    private boolean mTwoPane = false;

    private RecipeDetailViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        final Recipe recipe = getIntent().getParcelableExtra(EXTRA_RECIPE_DATA);
        if (recipe == null) {
            closeOnError();
            return;
        }
        setTitle(recipe.getName());

        // determine which layout we are in (tablet or phone)
        if (findViewById(R.id.fragment_step_detail) != null) {
            mTwoPane = true;
        }

        mViewModel = obtainViewModel(this);
        if (savedInstanceState == null) {
            mViewModel.init(recipe);
            setupViewFragment();
            saveRecipeDataToSharedPreferences(recipe);
            refreshWidgetIngredientsList();
        }

        // observe steps list click event
        mViewModel.getOpenStepDetailEvent().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                Step step = mViewModel.getCurrentStep().getValue();
                if (mTwoPane) {
                    StepDetailFragment fragment = StepDetailFragment.newInstance(step);
                    ActivityUtils.replaceFragmentInActivity(
                            getSupportFragmentManager(), fragment, R.id.fragment_step_detail);
                } else {
                    ArrayList<Step> steps = new ArrayList<>(recipe.getSteps());
                    Intent intent = new Intent(RecipeDetailsActivity.this, StepDetailActivity.class);
//                    intent.putExtra(StepDetailActivity.EXTRA_STEP, step);
                    intent.putParcelableArrayListExtra(StepDetailActivity.EXTRA_STEP_LIST, steps);
                    intent.putExtra(StepDetailActivity.EXTRA_POSITION, position);
                    startActivity(intent);
                }
            }
        });
    }

    private void refreshWidgetIngredientsList() {
        // refresh ingredients list
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplication());
        int ids[] = appWidgetManager.getAppWidgetIds(
                new ComponentName(getApplication(), RecipeWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(ids, R.id.widget_list);

        // we sent update intent to update widget recipe name
        Intent intent = new Intent(this, RecipeWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }

    private void saveRecipeDataToSharedPreferences(Recipe recipe) {
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(getString(R.string.recipe_name), recipe.getName());
        editor.putLong(getString(R.string.recipe_id), recipe.getId());
        editor.apply();
    }

    private void setupViewFragment() {
        if (mTwoPane) {
            return;
        }
//        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment.newInstance();
//        ActivityUtils.replaceFragmentInActivity(
//                getSupportFragmentManager(), recipeDetailFragment, R.id.fragment_recipe_detail);
    }

    public static RecipeDetailViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = Injection.provideViewModelFactory(activity);
        return ViewModelProviders.of(activity, factory).get(RecipeDetailViewModel.class);
    }

    private void closeOnError() {
        throw new IllegalArgumentException("Access denied.");
    }
}
