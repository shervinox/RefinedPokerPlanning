package com.appestan.refinedpokerplanning;

/**
 * Created with love and care by shervin on 03/02/2018.
 */

interface UIView {
    void setEstimateQuestionTo(String questionString);

    void displayResultInResultFlipView(String estimateFloat, String varianceFloat);

    void setResultFlipViewClickable(boolean state);

    void setEstimateFlipViewClickable(boolean b);

    void initEstimateFlipViewsClickListener();

    void displayResultToast(String estimateFloat, String varianceFloat);

    void flipAllFipViewsToFront();

    void logicalErrorToast(String message);
}
