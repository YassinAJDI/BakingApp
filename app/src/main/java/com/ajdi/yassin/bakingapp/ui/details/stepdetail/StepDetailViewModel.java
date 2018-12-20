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

//    private LiveData<Step> currentStep;

    private final SingleLiveEvent<Step> navigateToStepDetail = new SingleLiveEvent<>();

    public StepDetailViewModel() {
        Timber.d("StepDetailViewModel()");
        // update current step when current position changes
//        currentStep = Transformations.map(currentPosition, new Function<Integer, Step>() {
//            @Override
//            public Step apply(Integer input) {
//                Timber.d("Transformations");
//                return null;
//            }
//        });

//        navigateToStepDetail = Transformations.map(currentPosition, new Function<Integer, Step>() {
//            @Override
//            public Step apply(Integer input) {
//                return null;
//            }
//        });
    }

    public void init(List<Step> steps, int position) {
        Timber.d("Initializing viewModel");

        stepsList = steps;
        setCurrentPosition(position);
    }

//    public LiveData<Step> getCurrentStep() {
//        return currentStep;
//    }

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

    private void navigateToCurrentStep() {
        navigateToStepDetail.setValue(stepsList.get(currentPosition));
    }

    public SingleLiveEvent<Step> getNavigateToStepDetail() {
        return navigateToStepDetail;
    }

}
