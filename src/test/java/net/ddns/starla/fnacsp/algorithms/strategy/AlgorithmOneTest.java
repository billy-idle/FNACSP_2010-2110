package net.ddns.starla.fnacsp.algorithms.strategy;

import net.ddns.starla.fnacsp.algorithms.factory.AlgorithmFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AlgorithmOneTest extends AlgorithmTest {

    public AlgorithmOneTest(String zoneId, double hour, int day, int month, int year, double longitude, double latitude,
                            double pressure, double temperature, double expectedZenith, double expectedAzimuth,
                            double expectedRightAscension, double expectedDeclination, double expectedHourAngle) {

        super(zoneId, hour, day, month, year, longitude, latitude, pressure, temperature, expectedZenith, expectedAzimuth,
                expectedRightAscension, expectedDeclination, expectedHourAngle);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"UTC", 0, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.72042, -2.74575, -0.931114, -0.334789, -9.25708},
                {"UTC", 1, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.60326, -2.26739, -0.930352, -0.33462, -8.99533},
                {"UTC", 2, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.43559, -1.9524, -0.929589, -0.33445, -8.73357},
                {"UTC", 3, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.24778, -1.72687, -0.928827, -0.33428, -8.47182},
                {"UTC", 4, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.05348, -1.54347, -0.928065, -0.33411, -8.21006},
                {"UTC", 5, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.85995, -1.37702, -0.927303, -0.33394, -7.94831},
                {"UTC", 6, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.67257, -1.21235, -0.926541, -0.333769, -7.68656},
                {"UTC", 7, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.49366, -1.0383, -0.925779, -0.333599, -7.4248},
                {"UTC", 8, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.3379, -0.845367, -0.925017, -0.333428, -7.16305},
                {"UTC", 9, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.20734, -0.625778, -0.924255, -0.333257, -6.90129},
                {"UTC", 10, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.11359, -0.376131, -0.923493, -0.333086, -6.63954},
                {"UTC", 11, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.0672, -0.102074, -0.922732, -0.332915, -6.37778},
                {"UTC", 12, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.07461, 0.179739, -0.92197, -0.332743, -6.11603},
                {"UTC", 13, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.13472, 0.448445, -0.921209, -0.332572, -5.85427},
                {"UTC", 14, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.23954, 0.690121, -0.920448, -0.3324, -5.59252},
                {"UTC", 15, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.37812, 0.902021, -0.919687, -0.332228, -5.33076},
                {"UTC", 16, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.53759, 1.08917, -0.918925, -0.332056, -5.06901},
                {"UTC", 17, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.72232, 1.25997, -0.918164, -0.331883, -4.80725},
                {"UTC", 18, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 1.91178, 1.42433, -0.917403, -0.331711, -4.5455},
                {"UTC", 19, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.1059, 1.5942, -0.916643, -0.331538, -4.28374},
                {"UTC", 20, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.29901, 1.78676, -0.915882, -0.331365, -4.02199},
                {"UTC", 21, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.48262, 2.03176, -0.915121, -0.331192, -3.76023},
                {"UTC", 22, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.63966, 2.38468, -0.91436, -0.331019, -3.49847},
                {"UTC", 23, 25, 1, 2020, 0.21787, 0.73117, 1, 20, 2.7332, 2.91343, -0.9136, -0.330846, -3.23672}
        });
    }

    @Before
    public void computeSunPosition() {
        algorithm = new AlgorithmFactory().createInstance("AlgorithmOne");
        compute();
    }
}
