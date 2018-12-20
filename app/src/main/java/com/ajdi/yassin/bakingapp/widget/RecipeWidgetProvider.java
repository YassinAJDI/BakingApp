package com.ajdi.yassin.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.ajdi.yassin.bakingapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        // Here we setup the intent which points to the RecipeWidgetService which will
        // provide the views for this collection.
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        // When intents are compared, the extras are ignored, so we need to embed the extras
        // into the data so that the extras will not be ignored.
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        // Construct the RemoteViews object
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_recipe_ingredients);

        // Set up the RemoteViews object to use a RemoteViews adapter.
        // This adapter connect to a RemoteViewsService through the specified intent.
        remoteViews.setRemoteAdapter(R.id.widget_list, intent);
        // The empty view is displayed when the collection has no items. It should be a sibling
        // of the collection view.
        remoteViews.setEmptyView(R.id.widget_list, R.id.empty_view);

        // retrieve recipe data from sharedPreferences
//        SharedPreferences prefs = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE);
//        String recipeName = prefs.getString(context.getString(R.string.recipe_name), "No Recipe Added");
//        String ingredients = prefs.getString(context.getString(R.string.ingredients), "Nothing!!");
//        long recipeId = prefs.getLong(context.getString(R.string.recipe_id), -1);

        // Update remote views
//        views.setTextViewText(R.id.appwidget_recipe_name, recipeName);
//        views.setTextViewText(R.id.appwidget_ingredient, ingredients);

        // Add pending intent to this widget item
        // attach an on-click listener to the widget
//        Intent intent;
//        if (recipeId >= 0) {
//            intent = new Intent(context, RecipeDetailsActivity.class);
//            intent.putExtra(context.getString(R.string.recipe_id), recipeId);
//        } else {
//            intent = new Intent(context, RecipeListActivity.class);
//        }
//        intent.setAction()
//        int flags = Intent.FLAG_ACTIVITY_NEW_TASK;
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
//        views.setOnClickPendingIntent(R.id.widget_list_container, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
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

