package net.ddns.starla.fnacsp.template.algorithms;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public final class AlgorithmOne extends Algorithm {

    @Override
    public void accuracyLevel(double longitude) {
        double wte = 0.017202786 * te;
        double s1 = sin(wte);
        double c1 = cos(wte);
        double s2 = 2.0 * s1 * c1;
        double c2 = (c1 + s1) * (c1 - s1);

        rightAscension = -1.38880 + 1.72027920e-2 * te + 3.199e-2 * s1 - 2.65e-3 * c1 + 4.050e-2 * s2 + 1.525e-2 * c2;

        shiftRightAscensionToItsConventionalRange();

        declination = 6.57e-3 + 7.347e-2 * s1 - 3.9919e-1 * c1 + 7.3e-4 * s2 - 6.60e-3 * c2;
        hourAngle = 1.75283 + 6.3003881 * t + longitude - rightAscension;
    }

}