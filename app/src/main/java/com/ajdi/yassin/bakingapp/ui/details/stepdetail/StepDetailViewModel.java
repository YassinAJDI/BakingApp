package com.ajdi.yassin.bakingapp.ui.details.stepdetail;

import com.ajdi.yassin.bakingapp.data.model.Step;
import com.ajdi.yassin.bakingapp.utils.SingleLiveEvent;

import java.util.List;

import androidx.lifecycle.ViewModel;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 12/19/2018.
 */
public class StepDetailViewModel extends ViewModel {

    private List<Step> stepsList;
    private int currentPosition;
    private final SingleLiveEvent<Step> navigateToStepDetail = new SingleLiveEvent<>();

    public void init(List<Step> steps, int position) {
        Timber.d("Initializing viewModel");

        stepsList = steps;
        setCurrentPosition(position);
    }

    public void setCurrentPosition(int position) {
        currentPosition = position;
        navigateToCurrentStep();
    }

    public void nextStep() {
        currentPosition++;
        navigateToCurrentStep();
    }

    public void previousStep() {
        currentPosition--;
        navigateToCurrentStep();
    }

    public boolean hasNext() {
        return currentPosition < stepsList.size() - 1;
    }

    public boolean hasPrevious() {
        return currentPosition > 0;
    }

    private void navigateToCurrentStep() {
        navigateToStepDetail.setValue(stepsList.get(currentPosition));
    }

    public SingleLiveEvent<Step> getNavigateToStepDetail() {
        return navigateToStepDetail;
    }

}
