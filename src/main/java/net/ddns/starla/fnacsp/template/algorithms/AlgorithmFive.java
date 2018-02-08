package net.ddns.starla.fnacsp.template.algorithms;

import static java.lang.Math.*;

public final class AlgorithmFive extends Algorithm {

    @Override
    public void accuracyLevel(double longitude) {
        double wte = 0.0172019715 * te;
        double s1 = sin(wte);
        double c1 = cos(wte);
        double s2 = 2.0 * s1 * c1;
        double c2 = (c1 + s1) * (c1 - s1);
        double s3 = s2 * c1 + c2 * s1;
        double c3 = c2 * c1 - s2 * s1;

        double l = 1.7527901
                + 1.7202792159e-2 * te
                + 3.33024e-2 * s1 - 2.0582e-3 * c1
                + 3.512e-4 * s2 - 4.07e-5 * c2
                + 5.2e-6 * s3 - 9e-7 * c3
                - 8.23e-5 * s1 * sin(2.92e-5 * te)
                + 1.27e-5 * sin(1.49e-3 * te - 2.337)
                + 1.21e-5 * sin(4.31e-3 * te + 3.065)
                + 2.33e-5 * sin(1.076e-2 * te - 1.533)
                + 3.49e-5 * sin(1.575e-2 * te - 2.358)
                + 2.67e-5 * sin(2.152e-2 * te + 0.074)
                + 1.28e-5 * sin(3.152e-2 * te + 1.547)
                + 3.14e-5 * sin(2.1277e-1 * te - 0.488);

        double nu = 9.282e-4 * te - 0.8;
        double deltaLambda = 8.34e-5 * sin(nu);
        double lambda = l + PI + deltaLambda;
        double epsilon = 4.089567e-1 - 6.19e-9 * te + 4.46e-5 * cos(nu);
        double sl = sin(lambda);
        double cl = cos(lambda);
        double se = sin(epsilon);
        double ce = sqrt(1 - se * se);

        rightAscension = atan2(sl * ce, cl);

        if (rightAscension < 0.0)
            rightAscension += PI2;

        declination = asin(sl * se);
        hourAngle = 1.7528311 + 6.300388099 * t + longitude - rightAscension + 0.92 * deltaLambda;
    }

}