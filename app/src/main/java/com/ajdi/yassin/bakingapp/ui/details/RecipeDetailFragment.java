package com.ajdi.yassin.bakingapp.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Ingredient;
import com.ajdi.yassin.bakingapp.data.model.Step;
import com.ajdi.yassin.bakingapp.ui.details.ingredients.IngredientsAdapter;
import com.ajdi.yassin.bakingapp.ui.details.steps.StepsAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi
 * @since 12/12/2018.
 */
public class RecipeDetailFragment extends Fragment {

    private RecipeDetailViewModel mViewModel;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance() {
        return new RecipeDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = RecipeDetailsActivity.obtainViewModel(getActivity());
        setupIngredientsAdapter();
        setupStepsAdapter();
    }

    private void setupIngredientsAdapter() {
        RecyclerView listIngredients = getActivity().findViewById(R.id.rv_ingredients);
        final IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
        listIngredients.setAdapter(ingredientsAdapter);
        listIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));
        ViewCompat.setNestedScrollingEnabled(listIngredients, false);

        // observe ingredients list
        mViewModel.getIngredientsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                ingredientsAdapter.submitList(ingredients);
            }
        });
    }

    private void setupStepsAdapter() {
        RecyclerView listSteps = getActivity().findViewById(R.id.rv_steps);
        final StepsAdapter adapter = new StepsAdapter(mViewModel);
        listSteps.setAdapter(adapter);
        listSteps.setLayoutManager(new LinearLayoutManager(getActivity()));
        ViewCompat.setNestedScrollingEnabled(listSteps, false);

        // observe steps list
        mViewModel.getStepsList().observe(getViewLifecycleOwner(), new Observer<List<Step>>() {
            @Override
            public void onChanged(List<Step> steps) {
                adapter.submitList(steps);
            }
        });
    }
}
