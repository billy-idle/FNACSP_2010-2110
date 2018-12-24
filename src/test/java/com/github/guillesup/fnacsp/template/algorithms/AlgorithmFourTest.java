package com.github.guillesup.fnacsp.template.algorithms;

import com.github.guillesup.fnacsp.template.algorithms.factory.AlgorithmFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AlgorithmFourTest extends AlgorithmTest {

    public AlgorithmFourTest(String zoneId, double hour, int day, int month, int year, double longitude, double latitude,
                             double pressure, double temperature, double expectedZenith, double expectedAzimuth,
                             double expectedRightAscension, double expectedDeclination, double expectedHourAngle) {

        super(zoneId, hour, day, month, year, longitude, latitude, pressure, temperature, expectedZenith, expectedAzimuth,
                expectedRightAscension, expectedDeclination, expectedHourAngle);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"UTC", 0, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.7205, -2.75164, 5.35447, -0.334143, -9.25954},
                {"UTC", 1, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.60414, -2.27176, 5.35523, -0.333966, -8.99778},
                {"UTC", 2, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.43683, -1.95558, 5.35599, -0.33379, -8.73602},
                {"UTC", 3, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.24916, -1.7294, 5.35675, -0.333613, -8.47427},
                {"UTC", 4, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.05488, -1.54568, 5.35751, -0.333437, -8.21251},
                {"UTC", 5, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.86131, -1.3791, 5.35827, -0.33326, -7.95075},
                {"UTC", 6, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.67381, -1.21443, 5.35903, -0.333083, -7.689},
                {"UTC", 7, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.49469, -1.04047, 5.35979, -0.332905, -7.42724},
                {"UTC", 8, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.33869, -0.847702, 5.36055, -0.332728, -7.16548},
                {"UTC", 9, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.20777, -0.628311, 5.36131, -0.33255, -6.90373},
                {"UTC", 10, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.11358, -0.378814, 5.36207, -0.332372, -6.64197},
                {"UTC", 11, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.06667, -0.104755, 5.36282, -0.332194, -6.38021},
                {"UTC", 12, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.07357, 0.177269, 5.36358, -0.332016, -6.11846},
                {"UTC", 13, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.13325, 0.446342, 5.36434, -0.331838, -5.8567},
                {"UTC", 14, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.23776, 0.688416, 5.3651, -0.331659, -5.59494},
                {"UTC", 15, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.37613, 0.900656, 5.36586, -0.33148, -5.33318},
                {"UTC", 16, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.53561, 1.08805, 5.36662, -0.331302, -5.07143},
                {"UTC", 17, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.72011, 1.25902, 5.36738, -0.331123, -4.80967},
                {"UTC", 18, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.90952, 1.42345, 5.36814, -0.330943, -4.54791},
                {"UTC", 19, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.10363, 1.59327, 5.3689, -0.330764, -4.28615},
                {"UTC", 20, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.29676, 1.78561, 5.36965, -0.330584, -4.02439},
                {"UTC", 21, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.48046, 2.03004, 5.37041, -0.330405, -3.76264},
                {"UTC", 22, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.63776, 2.3816, 5.37117, -0.330225, -3.50088},
                {"UTC", 23, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.732, 2.90817, 5.37193, -0.330044, -3.23912}
        });
    }

    @Before
    public void computeSunPosition() {
        algorithm = AlgorithmFactory.createInstance("AlgorithmFour", decimalTimeToZonedDateTime(),
                longitude, latitude, pressure, temperature);
        compute();
    }
}
