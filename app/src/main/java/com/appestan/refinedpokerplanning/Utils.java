package com.appestan.refinedpokerplanning;

import eu.davidea.flipview.FlipView;

/**
 * Created with love and care by shervin on 03/02/2018.
 */

class Utils {
    private static final String TAG = "Utils";

    static void setOnClickListeners(MainActivity mainActivity) {
        setEstimationFlipViewClickListener(mainActivity);
        setResultFlipViewFlippingListener(mainActivity);
    }

    private static void setResultFlipViewFlippingListener(final MainActivity mainActivity) {
        ((FlipView) mainActivity.findViewById(R.id.result_flipview)).setOnFlippingListener(new FlipView.OnFlippingListener() {
            @Override
            public void onFlipped(FlipView flipView, boolean checked) {
                flipView.flip(checked);
                FlipView standardDeviation = mainActivity.findViewById(R.id.standard_deviation_flipview);
                standardDeviation.flip(checked);
            }
        });

        ((FlipView) mainActivity.findViewById(R.id.standard_deviation_flipview)).setOnFlippingListener(new FlipView.OnFlippingListener() {
            @Override
            public void onFlipped(FlipView flipView, boolean checked) {
                flipView.flip(checked);
                FlipView resultFlipView = mainActivity.findViewById(R.id.result_flipview);
                resultFlipView.flip(checked);
            }
        });
    }

    static void setEstimationFlipViewClickListener(MainActivity mainActivity) {
        mainActivity.findViewById(R.id.flipView_0).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_1).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_2).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_3).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_4).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_5).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_6).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_7).setOnClickListener(mainActivity);
        mainActivity.findViewById(R.id.flipView_8).setOnClickListener(mainActivity);
    }

    static void flipToFront(FlipView flipView) {
        flipView.flip(false);

    }

    static String FloatToIntNicelyFormatter(float givenFloat) {
        float delta = givenFloat - (int) givenFloat;
        if (delta == 0) {
            return (int) givenFloat + "";
        } else {
            return String.format("%.1f", givenFloat);
        }
    }
}
