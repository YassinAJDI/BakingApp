package com.ajdi.yassin.bakingapp.utils;

import android.content.Context;

import com.ajdi.yassin.bakingapp.data.RecipeRepository;
import com.ajdi.yassin.bakingapp.data.local.RecipesDatabase;
import com.ajdi.yassin.bakingapp.data.remote.ApiClient;
import com.ajdi.yassin.bakingapp.data.remote.RecipeService;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class Injection {
    public static ViewModelFactory provideViewModelFactory(Context context) {
        RecipeRepository repository = provideRecipeRepository(context);
        return ViewModelFactory.getInstance(repository);
    }

    public static RecipeRepository provideRecipeRepository(Context context) {
        RecipeService apiService = ApiClient.getInstance();
        AppExecutors executors = AppExecutors.getInstance();
        RecipesDatabase database = RecipesDatabase.getInstance(context.getApplicationContext());
        return RecipeRepository.getInstance(
                executors,
                apiService,
                database);
    }
}
