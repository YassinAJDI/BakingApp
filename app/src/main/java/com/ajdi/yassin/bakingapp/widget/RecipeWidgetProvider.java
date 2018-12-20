package com.ajdi.yassin.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.ui.recipedetail.RecipeDetailsActivity;
import com.ajdi.yassin.bakingapp.ui.recipelist.RecipeListActivity;
import com.ajdi.yassin.bakingapp.utils.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

        // retrieve recipe data from sharedPreferences
        SharedPreferences prefs = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE);
        String recipeName = prefs.getString(context.getString(R.string.recipe_name), "No Recipe Added");
        String ingredients = prefs.getString(context.getString(R.string.ingredients), "Nothing!!");
        long recipeId = prefs.getLong(context.getString(R.string.recipe_id), -1);

        // Update remote views
        views.setTextViewText(R.id.appwidget_recipe_name, recipeName);
        views.setTextViewText(R.id.appwidget_ingredient, ingredients);

        // Add pending intent to this widget item
        // When widget is clicked open recipe list activity
        Intent intent;
        if (recipeId >= 0) {
            intent = new Intent(context, RecipeDetailsActivity.class);
            intent.putExtra(context.getString(R.string.recipe_id), recipeId);
        } else {
            intent = new Intent(context, RecipeListActivity.class);
        }
//        intent.setAction()
//        int flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_list_container, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

