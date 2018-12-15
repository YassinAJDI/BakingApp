package com.ajdi.yassin.bakingapp.ui.details;

import android.os.Bundle;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Recipe;
import com.ajdi.yassin.bakingapp.utils.ActivityUtils;

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

        Recipe recipe = getIntent().getParcelableExtra(EXTRA_RECIPE_DATA);
        if (recipe == null) {
            closeOnError();
            return;
        }

        // determine which layout we are in (tablet or phone)
        if (findViewById(R.id.fragment_step_detail) != null) {
            mTwoPane = true;
        }

        mViewModel = obtainViewModel(this);
        if (savedInstanceState == null) {
            setupViewFragment();
            mViewModel.init(recipe);
        }

        // observe steps list click event
        mViewModel.getOpenStepDetailEvent().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                if (mTwoPane) {
                    mViewModel.setCurrentStep(position);
                    StepDetailFragment fragment = StepDetailFragment.newInstance();
                    ActivityUtils.replaceFragmentInActivity(
                            getSupportFragmentManager(), fragment, R.id.fragment_step_detail);
                }
            }
        });
    }

    private void setupViewFragment() {
        if (mTwoPane) {
            return;
        }
        RecipeDetailFragment discoverMoviesFragment = RecipeDetailFragment.newInstance();
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), discoverMoviesFragment, R.id.fragment_recipe_detail);
    }

    public static RecipeDetailViewModel obtainViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity).get(RecipeDetailViewModel.class);
    }

    private void closeOnError() {
        throw new IllegalArgumentException("Access denied.");
    }
}
