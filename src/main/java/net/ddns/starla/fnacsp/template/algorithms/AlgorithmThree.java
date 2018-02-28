package net.ddns.starla.fnacsp.template.algorithms;

import net.ddns.starla.fnacsp.template.entities.AtmPressure;
import net.ddns.starla.fnacsp.template.entities.Coordinates;
import net.ddns.starla.fnacsp.template.entities.Temperature;
import net.ddns.starla.fnacsp.template.entities.Time;

import static java.lang.Math.*;

public final class AlgorithmThree extends Algorithm {

    public AlgorithmThree(Time time, Coordinates coordinates, AtmPressure atmPressure, Temperature temperature) {
        super(time, coordinates, atmPressure, temperature);
    }

    @Override
    public void accuracyLevel() {
        double wte = 0.0172019715 * te;
        double lambda = -1.388803 + 1.720279216e-2 * te + 3.3366e-2 * sin(wte - 0.06172) + 3.53e-4 *
                sin(2.0 * wte - 0.1163);
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
    }
}