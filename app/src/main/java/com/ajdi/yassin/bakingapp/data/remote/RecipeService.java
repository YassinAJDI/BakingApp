package com.ajdi.yassin.bakingapp.data.remote;

import com.ajdi.yassin.bakingapp.data.remote.model.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Udacity recipes REST API access points.
 * <p>
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public interface RecipeService {

    @GET("baking.json")
    Call<Recipe> getAllRecipes();
}
