package com.ajdi.yassin.bakingapp.ui.details.stepdetail;

import android.os.Bundle;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Step;
import com.ajdi.yassin.bakingapp.utils.ActivityUtils;

import androidx.appcompat.app.AppCompatActivity;

public class StepDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STEP = "EXTRA_STEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Step step = getIntent().getParcelableExtra(EXTRA_STEP);
        if (savedInstanceState == null) {
            setupViewFragment(step);
        }
    }

    private void setupViewFragment(Step step) {
        StepDetailFragment fragment = StepDetailFragment.newInstance(step);
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), fragment, R.id.fragment_step_detail);
    }
}
