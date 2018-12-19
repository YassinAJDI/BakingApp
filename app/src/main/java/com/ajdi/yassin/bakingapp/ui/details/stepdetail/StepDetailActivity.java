package com.ajdi.yassin.bakingapp.ui.details.stepdetail;

import android.os.Bundle;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Step;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import timber.log.Timber;

public class StepDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STEP = "EXTRA_STEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Timber.d("onCreate");

        Step step = getIntent().getParcelableExtra(EXTRA_STEP);
        setTitle("Step Detail");

        if (savedInstanceState == null) {
            setupViewFragment(step);
        }
    }

    private void setupViewFragment(Step step) {
        StepDetailFragment fragment = StepDetailFragment.newInstance(step);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_step_detail, fragment);
        transaction.commit();
//        ActivityUtils.replaceFragmentInActivity(
//                getSupportFragmentManager(), fragment, R.id.fragment_step_detail);
    }
}
