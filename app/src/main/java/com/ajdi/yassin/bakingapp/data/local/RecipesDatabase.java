package com.ajdi.yassin.bakingapp.data.local;

import android.content.Context;

import com.ajdi.yassin.bakingapp.data.local.dao.IngredientsDao;
import com.ajdi.yassin.bakingapp.data.local.dao.RecipesDao;
import com.ajdi.yassin.bakingapp.data.local.model.Ingredient;
import com.ajdi.yassin.bakingapp.data.local.model.Recipe;
import com.ajdi.yassin.bakingapp.data.local.model.Step;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author Yassin Ajdi
 * @since 12/20/2018.
 */
@Database(
        entities = {Recipe.class, Step.class, Ingredient.class},
        version = 1,
        exportSchema = false)
public abstract class RecipesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "Recipes.db";

    private static RecipesDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract RecipesDao recipesDao();

    public abstract IngredientsDao ingredientsDao();

    public static RecipesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context);
            }
            return INSTANCE;
        }
    }

    private static RecipesDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                RecipesDatabase.class,
                DATABASE_NAME).build();
    }
}
