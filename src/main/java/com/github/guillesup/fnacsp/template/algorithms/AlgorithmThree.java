package com.github.guillesup.fnacsp.template.algorithms;

import com.github.guillesup.fnacsp.template.entities.Entity;

public final class AlgorithmThree extends Algorithm {

    public AlgorithmThree(Entity time, Entity longitude, Entity latitude, Entity atmPressure, Entity temperature) {
        super(time, longitude, latitude, atmPressure, temperature);
    }

    public void accuracyLevel() {
        double wte = 0.0172019715 * te;
        double lambda = -1.388803 + 1.720279216e-2 * te + 3.3366e-2 * Math.sin(wte - 0.06172) + 3.53e-4 *
                Math.sin(2.0 * wte - 0.1163);
        double epsilon = 4.089567e-1 - 6.19e-9 * te;
        double sl = Math.sin(lambda);
        double cl = Math.cos(lambda);
        double se = Math.sin(epsilon);
        double ce = Math.sqrt(1 - se * se);

        rightAscension = Math.atan2(sl * ce, cl);

        if (rightAscension < 0.0)
            rightAscension += PI2;

        declination = Math.asin(sl * se);
        hourAngle = 1.7528311 + 6.300388099 * t + longitude - rightAscension;
    }
}