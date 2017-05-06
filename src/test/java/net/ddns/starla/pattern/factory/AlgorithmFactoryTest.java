package net.ddns.starla.pattern.factory;

import net.ddns.starla.pattern.strategy.Algorithm;
import org.junit.Test;

public class AlgorithmFactoryTest {

    private Algorithm algorithm;

    @Test
    public void canCreateInstanceOfAlgorithmOne() throws Exception {
        algorithm = new AlgorithmFactory().getInstance(Accuracy.LOWEST);
    }

    @Test
    public void canCreateInstanceOfAlgorithmTwo() throws Exception {
        algorithm = new AlgorithmFactory().getInstance(Accuracy.LOW);
    }

    @Test
    public void canCreateInstanceOfAlgorithmThree() throws Exception {
        algorithm = new AlgorithmFactory().getInstance(Accuracy.MID);
    }

    @Test
    public void canCreateInstanceOfAlgorithmFour() throws Exception {
        algorithm = new AlgorithmFactory().getInstance(Accuracy.HIGH);
    }

    @Test
    public void canCreateInstanceOfAlgorithmFive() throws Exception {
        algorithm = new AlgorithmFactory().getInstance(Accuracy.HIGHEST);
    }
}
