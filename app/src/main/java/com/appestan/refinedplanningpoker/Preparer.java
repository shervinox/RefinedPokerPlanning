package com.appestan.refinedplanningpoker;

import android.util.Log;
import android.view.View;

import eu.davidea.flipview.FlipView;

import static com.appestan.refinedplanningpoker.CURRENT_STATE.ENTERING_BEST_CASE;
import static com.appestan.refinedplanningpoker.CURRENT_STATE.ENTERING_NOMINAL_CASE;
import static com.appestan.refinedplanningpoker.CURRENT_STATE.ENTERING_WORST_CASE;

/**
 * Created with love and care by shervin on 03/02/2018.
 */

class Preparer {
    private static final String TAG = "Preparer";

    private final UIView mainActivity;
    private final RefinedEstimator refinedEstimator;
    private CURRENT_STATE currentState;
    private boolean needToReset = false;

    Preparer(UIView mainActivity) {
        this.mainActivity = mainActivity;
        currentState = ENTERING_BEST_CASE;
        refinedEstimator = new RefinedEstimator();
        mainActivity.setResultFlipViewClickable(false);
    }

    void estimated(View selectedFlipView) {
        Log.d(TAG, "estimated: " + selectedFlipView.getTag().toString());
        if (selectedFlipView.getTag() == null) {
            return;
        }

        Object tag = selectedFlipView.getTag();
        String estimateValueStr = tag.toString();
        float estimatedValueFloat = Float.parseFloat(estimateValueStr);

        switch (currentState) {
            case ENTERING_BEST_CASE:

                ((FlipView) selectedFlipView).flip(!((FlipView) selectedFlipView).isFlipped());
                needToReset = true;
                refinedEstimator.setBestCaseEstimateInt(estimatedValueFloat);
                mainActivity.setEstimateQuestionTo("Estimate Normal Case:");
                currentState = ENTERING_NOMINAL_CASE;
                break;

            case ENTERING_NOMINAL_CASE:

                refinedEstimator.setNominalCaseEstimateInt(estimatedValueFloat);
                if (!checkLogic()) {
                    return;
                }
                ((FlipView) selectedFlipView).flip(!((FlipView) selectedFlipView).isFlipped());
                mainActivity.setEstimateQuestionTo("Estimate Worst Case:");
                currentState = ENTERING_WORST_CASE;

                break;
            case ENTERING_WORST_CASE:
                refinedEstimator.setWorstCaseEstimateInt(estimatedValueFloat);
                if (!checkLogic()) {
                    return;
                }

                ((FlipView) selectedFlipView).flip(!((FlipView) selectedFlipView).isFlipped());
                displayResultOfEstimation();
                mainActivity.setEstimateQuestionTo("Estimating is Complete");
                currentState = ENTERING_BEST_CASE;

                break;
        }
    }

    private boolean checkLogic() {
        switch (currentState) {
            case ENTERING_BEST_CASE:
                return true;
            case ENTERING_NOMINAL_CASE:
                if (refinedEstimator.getNominalCaseEstimateInt() <= refinedEstimator.getBestCaseEstimateInt()) {
                    reset();
                    mainActivity.logicalErrorToast("Nominal case cant be shorter than Best case!");
                    return false;
                }

                break;
            case ENTERING_WORST_CASE:
                if (refinedEstimator.getWorstCaseEstimateInt() <= refinedEstimator.getNominalCaseEstimateInt()) {
                    reset();
                    mainActivity.logicalErrorToast("Worst case cant be shorter than Nominal case!");
                    return false;
                }

                break;
        }
        return true;
    }

    private void displayResultOfEstimation() {
        float expectedDuration = refinedEstimator.expectedDuration();
        float standardDeviation = refinedEstimator.standardDeviation();
        String expectedDurationInt = Utils.FloatToIntNicelyFormatter(expectedDuration);
        String standardDeviationInt = Utils.FloatToIntNicelyFormatter(standardDeviation);
        mainActivity.displayResultInResultFlipView(expectedDurationInt, standardDeviationInt);
        mainActivity.setResultFlipViewClickable(true);
        mainActivity.setEstimateFlipViewClickable(false);
        mainActivity.displayResultToast(expectedDurationInt, standardDeviationInt);
    }

    void reset() {
        needToReset = false;
        currentState = ENTERING_BEST_CASE;
        mainActivity.setEstimateQuestionTo("Estimate Best Case:");
        mainActivity.flipAllFipViewsToFront();
        mainActivity.setResultFlipViewClickable(false);
        mainActivity.setEstimateFlipViewClickable(true);
        mainActivity.initEstimateFlipViewsClickListener();
    }

    boolean needToReset() {
        return needToReset;
    }
}
