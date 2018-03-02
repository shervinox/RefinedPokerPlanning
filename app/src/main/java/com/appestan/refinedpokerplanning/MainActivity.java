package com.appestan.refinedpokerplanning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import eu.davidea.flipview.FlipView;

public class MainActivity extends AppCompatActivity implements UIView, View.OnClickListener {
    private Preparer preparer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setOnClickListeners(this);
        preparer = new Preparer(this);
    }

    @Override
    public void onClick(View view) {
        flipViewIsSelectedAsEstimate(view);
    }

    private void flipViewIsSelectedAsEstimate(View selectedFlipView) {
        this.preparer.estimated(selectedFlipView);
    }


    @Override
    public void setEstimateQuestionTo(String questionString) {
        ((TextView) this.findViewById(R.id.estimate_question_textview)).setText(questionString);
    }

    @Override
    public void displayResultInResultFlipView(String estimateFloat, String varianceFloat) {
        FlipView resultFlipView = this.findViewById(R.id.result_flipview);
        ((TextView) resultFlipView.getRearLayout().findViewById(R.id.textview)).setText(estimateFloat + "");
        FlipView standarDeviationFlipView = this.findViewById(R.id.standard_deviation_flipview);
        ((TextView) standarDeviationFlipView.getRearLayout().findViewById(R.id.textview)).setText(varianceFloat + "");
    }

    @Override
    public void setResultFlipViewClickable(boolean state) {
        findViewById(R.id.result_flipview).setClickable(state);
        findViewById(R.id.standard_deviation_flipview).setClickable(state);
    }

    @Override
    public void setEstimateFlipViewClickable(boolean state) {
        findViewById(R.id.flipView_0).setClickable(state);
        findViewById(R.id.flipView_1).setClickable(state);
        findViewById(R.id.flipView_2).setClickable(state);
        findViewById(R.id.flipView_3).setClickable(state);
        findViewById(R.id.flipView_4).setClickable(state);
        findViewById(R.id.flipView_5).setClickable(state);
        findViewById(R.id.flipView_6).setClickable(state);
        findViewById(R.id.flipView_7).setClickable(state);
        findViewById(R.id.flipView_8).setClickable(state);
    }

    @Override
    public void initEstimateFlipViewsClickListener() {
        Utils.setEstimationFlipViewClickListener(this);

    }

    @Override
    public void displayResultToast(String estimateFloat, String varianceFloat) {
        Toast.makeText(this, "Expected: " + estimateFloat + ", \nDeviation: " + varianceFloat, Toast.LENGTH_LONG).show();
    }

    @Override
    public void flipAllFipViewsToFront() {
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_0));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_1));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_2));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_3));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_4));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_5));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_6));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_7));
        Utils.flipToFront((FlipView) this.findViewById(R.id.flipView_8));
        Utils.flipToFront((FlipView) this.findViewById(R.id.result_flipview));
        Utils.flipToFront((FlipView) this.findViewById(R.id.standard_deviation_flipview));
    }

    @Override
    public void logicalErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (preparer.needToReset()) {
            preparer.reset();
        } else {
            super.onBackPressed();
        }
    }
}
