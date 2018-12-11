package com.ajdi.yassin.bakingapp.ui.list;

import android.os.Bundle;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.remote.model.Recipe;
import com.ajdi.yassin.bakingapp.utils.Injection;
import com.ajdi.yassin.bakingapp.utils.ViewModelFactory;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class RecipeListActivity extends AppCompatActivity {

    private RecipeListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        viewModel = obtainViewModel();
        setupListAdapter();
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = findViewById(R.id.rv_recipe_list);
        final RecipesAdapter adapter = new RecipesAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // observe recipe list
        viewModel.getListLiveData().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                adapter.submitList(recipes);
            }
        });
    }

    private RecipeListViewModel obtainViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory();
        return ViewModelProviders.of(this, factory).get(RecipeListViewModel.class);
    }
}
