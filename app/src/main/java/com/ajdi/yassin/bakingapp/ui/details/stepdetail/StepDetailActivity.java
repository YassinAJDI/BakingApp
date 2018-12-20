package com.ajdi.yassin.bakingapp.ui.details.stepdetail;

import android.os.Bundle;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Step;
import com.ajdi.yassin.bakingapp.utils.ActivityUtils;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import timber.log.Timber;

public class StepDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STEP = "EXTRA_STEP";
    public static final String EXTRA_STEP_LIST = "EXTRA_STEP_LIST";
    public static final String EXTRA_POSITION = "EXTRA_POSITION";

    private StepDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Timber.d("onCreate");

//        Step step = getIntent().getParcelableExtra(EXTRA_STEP);
        ArrayList<Step> steps = getIntent().getParcelableArrayListExtra(EXTRA_STEP_LIST);
        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);

        setTitle("Step Detail");
        mViewModel = obtainViewModel(this);
        if (savedInstanceState == null) {
            mViewModel.init(steps, position);
        }

//        // observe current step
//        mViewModel.getCurrentStep().observe(this, new Observer<Step>() {
//            @Override
//            public void onChanged(Step step) {
//                Timber.d("getCurrentStep()");
//            }
//        });

        mViewModel.getNavigateToStepDetail().observe(this, new Observer<Step>() {
            @Override
            public void onChanged(Step step) {
                Timber.d("navigateToStepDetail");
                StepDetailFragment fragment = StepDetailFragment.newInstance(step);
                ActivityUtils.replaceFragmentInActivity(
                        getSupportFragmentManager(), fragment, R.id.fragment_step_detail);
            }
        });
    }

    public static StepDetailViewModel obtainViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity).get(StepDetailViewModel.class);
    }

    private void setupViewFragment() {

    }
}
