package net.ddns.starla.fnacsp.pattern.factory;

import org.junit.Test;

import static net.ddns.starla.fnacsp.algorithms.strategy.down.Accuracy.*;
import static net.ddns.starla.fnacsp.algorithms.strategy.down.AlgorithmFactory.getInstance;

public class AlgorithmFactoryTest {

    @Test
    public void canCreateInstanceOfAlgorithmOne() throws Exception {
        getInstance(LOWEST);
    }

    @Test
    public void canCreateInstanceOfAlgorithmTwo() throws Exception {
        getInstance(LOW);
    }

    @Test
    public void canCreateInstanceOfAlgorithmThree() throws Exception {
        getInstance(MID);
    }

    @Test
    public void canCreateInstanceOfAlgorithmFour() throws Exception {
        getInstance(HIGH);
    }

    @Test
    public void canCreateInstanceOfAlgorithmFive() throws Exception {
        getInstance(HIGHEST);
    }
}
