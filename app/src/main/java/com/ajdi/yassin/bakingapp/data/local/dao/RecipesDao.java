package com.ajdi.yassin.bakingapp.data.local.dao;

import com.ajdi.yassin.bakingapp.data.local.model.Recipe;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

/**
 * @author Yassin Ajdi
 * @since 12/20/2018.
 */
@Dao
public interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecipe(Recipe recipe);

}
