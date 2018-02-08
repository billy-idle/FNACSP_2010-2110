package net.ddns.starla.fnacsp.factory;

import net.ddns.starla.fnacsp.template.algorithms.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmFactoryTest {
    private static AlgorithmFactory algorithmFactory;

    @Before
    public void setUp() {
        //algorithmFactory = new AlgorithmFactory();
    }

    @Test
    public void createInstanceOfAlgorithmOne() {
        assertEquals(AlgorithmOne.class, AlgorithmFactory.createInstance("AlgorithmOne").getClass());
    }

    @Test
    public void createInstanceOfAlgorithmTwo() {
        assertEquals(AlgorithmTwo.class, AlgorithmFactory.createInstance("AlgorithmTwo").getClass());
    }

    @Test
    public void createInstanceOfAlgorithmThree() {
        assertEquals(AlgorithmThree.class, AlgorithmFactory.createInstance("AlgorithmThree").getClass());
    }

    @Test
    public void createInstanceOfAlgorithmFour() {
        assertEquals(AlgorithmFour.class, AlgorithmFactory.createInstance("AlgorithmFour").getClass());
    }

    @Test
    public void createInstanceOfAlgorithmFive() {
        assertEquals(AlgorithmFive.class, AlgorithmFactory.createInstance("AlgorithmFive").getClass());
    }

    @Test
    public void createInstanceOfNonExistentClass() {
        assertEquals(NullAlgorithm.class, AlgorithmFactory.createInstance("FooBar").getClass());
    }

    @Test
    public void createInstanceOfNonAlgorithmClass() {
        assertEquals(NullAlgorithm.class, AlgorithmFactory.createInstance("DummyClass").getClass());
    }
}