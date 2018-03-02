package com.appestan.refinedpokerplanning;

/**
 * Created with love and care by shervin on 03/02/2018.
 */

class RefinedEstimator {
    private float bestCaseEstimateInt;
    private float nominalCaseEstimateInt;
    private float worstCaseEstimateInt;

    float getBestCaseEstimateInt() {
        return bestCaseEstimateInt;
    }

    void setBestCaseEstimateInt(float bestCaseEstimateInt) {
        this.bestCaseEstimateInt = bestCaseEstimateInt;
    }

    float getNominalCaseEstimateInt() {
        return nominalCaseEstimateInt;
    }

    void setNominalCaseEstimateInt(float nominalCaseEstimateInt) {
        this.nominalCaseEstimateInt = nominalCaseEstimateInt;
    }

    float getWorstCaseEstimateInt() {
        return worstCaseEstimateInt;
    }

    void setWorstCaseEstimateInt(float worstCaseEstimateInt) {
        this.worstCaseEstimateInt = worstCaseEstimateInt;
    }

    float expectedDuration() {
        return (bestCaseEstimateInt + (4 * nominalCaseEstimateInt) + worstCaseEstimateInt) / 6;
    }

    float standardDeviation() {
        return (worstCaseEstimateInt - bestCaseEstimateInt) / 6;
    }

}
