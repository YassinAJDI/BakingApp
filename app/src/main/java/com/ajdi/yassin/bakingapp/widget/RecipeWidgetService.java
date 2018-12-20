package com.ajdi.yassin.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.RecipeRepository;
import com.ajdi.yassin.bakingapp.data.local.model.Ingredient;
import com.ajdi.yassin.bakingapp.utils.Injection;

import java.util.ArrayList;
import java.util.List;

public class RecipeWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<Ingredient> ingredientList = new ArrayList<>();
    private Context mContext;

    public RecipeRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        // This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
        // on the collection view corresponding to this factory. You can do heaving lifting in
        // here, synchronously. For example, if you need to process an image, fetch something
        // from the network, etc., it is ok to do it here, synchronously. The widget will remain
        // in its current state while work is being done here, so you don't need to worry about
        // locking up the widget.
        ingredientList.clear();
        RecipeRepository repository = Injection.provideRecipeRepository(mContext);

        List<Ingredient> ingredients = repository.getAllIngredients();
        ingredientList.addAll(ingredients);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // We construct a remote views item based on our widget item xml file, and set the
        // text based on the position.
        final RemoteViews remoteView = new RemoteViews(mContext.getPackageName(), R.layout.item_widget_row);
        Ingredient ingredient = ingredientList.get(position);
        remoteView.setTextViewText(R.id.text_widget_ingredient_name, ingredient.getIngredient());
        remoteView.setTextViewText(R.id.text_widget_ingredient_quantity,
                ingredient.getMeasure() + " " + ingredient.getQuantity());

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
