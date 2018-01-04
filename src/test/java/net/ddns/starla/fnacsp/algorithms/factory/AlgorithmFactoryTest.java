package net.ddns.starla.fnacsp.algorithms.factory;

import net.ddns.starla.fnacsp.algorithms.strategy.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmFactoryTest {
    private static AlgorithmFactory algorithmFactory;

    @Before
    public void setUp() {
        algorithmFactory = new AlgorithmFactory();
    }

    @Test
    public void createInstanceOfAlgorithmOne() {
        Algorithm algorithm = algorithmFactory.createInstance("AlgorithmOne");
        assertEquals(AlgorithmOne.class, algorithm.getClass());
    }

    @Test
    public void createInstanceOfAlgorithmTwo() {
        Algorithm algorithm = algorithmFactory.createInstance("AlgorithmTwo");
        assertEquals(AlgorithmTwo.class, algorithm.getClass());
    }

    @Test
    public void createInstanceOfAlgorithmThree() {
        Algorithm algorithm = algorithmFactory.createInstance("AlgorithmThree");
        assertEquals(AlgorithmThree.class, algorithm.getClass());
    }

    @Test
    public void createInstanceOfAlgorithmFour() {
        Algorithm algorithm = algorithmFactory.createInstance("AlgorithmFour");
        assertEquals(AlgorithmFour.class, algorithm.getClass());
    }

    @Test
    public void createInstanceOfAlgorithmFive() {
        Algorithm algorithm = algorithmFactory.createInstance("AlgorithmFive");
        assertEquals(AlgorithmFive.class, algorithm.getClass());
    }
}