package com.ajdi.yassin.bakingapp.data.local.dao;

import com.ajdi.yassin.bakingapp.data.local.model.Ingredient;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @author Yassin Ajdi
 * @since 12/20/2018.
 */
@Dao
public interface IngredientsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllIngredients(List<Ingredient> ingredients);

    @Query("SELECT * FROM ingredient")
    List<Ingredient> getAllIngredients();

}
