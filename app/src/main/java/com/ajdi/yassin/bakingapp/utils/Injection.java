package com.ajdi.yassin.bakingapp.utils;

import com.ajdi.yassin.bakingapp.data.RecipeRepository;
import com.ajdi.yassin.bakingapp.data.remote.ApiClient;
import com.ajdi.yassin.bakingapp.data.remote.RecipeService;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class Injection {
    public static ViewModelFactory provideViewModelFactory() {
        RecipeRepository repository = provideRecipeRepository();
        return ViewModelFactory.getInstance(repository);
    }

    private static RecipeRepository provideRecipeRepository() {
        RecipeService apiService = ApiClient.getInstance();
        AppExecutors executors = AppExecutors.getInstance();
        return RecipeRepository.getInstance(
                executors,
                apiService);
    }
}
