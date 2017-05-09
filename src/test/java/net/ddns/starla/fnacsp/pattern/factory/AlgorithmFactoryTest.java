package net.ddns.starla.fnacsp.pattern.factory;

import org.junit.Test;

public class AlgorithmFactoryTest {

    @Test
    public void canCreateInstanceOfAlgorithmOne() throws Exception {
        new AlgorithmFactory().getInstance(Accuracy.LOWEST);
    }

    @Test
    public void canCreateInstanceOfAlgorithmTwo() throws Exception {
        new AlgorithmFactory().getInstance(Accuracy.LOW);
    }

    @Test
    public void canCreateInstanceOfAlgorithmThree() throws Exception {
        new AlgorithmFactory().getInstance(Accuracy.MID);
    }

    @Test
    public void canCreateInstanceOfAlgorithmFour() throws Exception {
        new AlgorithmFactory().getInstance(Accuracy.HIGH);
    }

    @Test
    public void canCreateInstanceOfAlgorithmFive() throws Exception {
        new AlgorithmFactory().getInstance(Accuracy.HIGHEST);
    }
}
