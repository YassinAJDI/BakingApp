package com.ajdi.yassin.bakingapp.ui.details;

import android.os.Bundle;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Recipe;
import com.ajdi.yassin.bakingapp.utils.ActivityUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class RecipeDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_DATA = "extra_recipe";

    private RecipeDetailViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Recipe recipe = getIntent().<Recipe>getParcelableExtra(EXTRA_RECIPE_DATA);
        if (recipe == null) {
            closeOnError();
            return;
        }

        if (savedInstanceState == null) {
            setupViewFragment();
        }
        mViewModel = obtainViewModel(this);
        // TODO: 12/12/2018 only run once
        mViewModel.init(recipe);
    }

    private void setupViewFragment() {
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
