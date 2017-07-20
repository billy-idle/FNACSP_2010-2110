package net.ddns.starla.fnacsp.algorithms.strategy.down;

import net.ddns.starla.fnacsp.algorithms.strategy.top.Algorithm;

import static java.lang.Math.*;

final class AlgorithmThree extends Algorithm {
    @Override
    public void compute(double hour, int day, int month, int year, double longitude,
                        double latitude, double pressure, double temperature) {

        timeScaleComputation(hour, day, month, year);

        double wte = 0.0172019715 * te;
        double lambda = -1.388803 + 1.720279216e-2 * te + 3.3366e-2 * sin(wte - 0.06172) + 3.53e-4 * sin(2.0 * wte - 0.1163);
        double epsilon = 4.089567e-1 - 6.19e-9 * te;
        double sl = sin(lambda);
        double cl = cos(lambda);
        double se = sin(epsilon);
        double ce = sqrt(1 - se * se);

        rightAscension = atan2(sl * ce, cl);

        if (rightAscension < 0.0)
            rightAscension += PI2;

        declination = asin(sl * se);

        hourAngle = 1.7528311 + 6.300388099 * t + longitude - rightAscension;

        shiftHourAngleToItsConventionalRange();
        applyFinalComputationallyOptimizedProcedure(latitude, pressure, temperature);
    }
}