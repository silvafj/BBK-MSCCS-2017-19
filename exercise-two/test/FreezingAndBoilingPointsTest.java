import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FreezingAndBoilingPointsTest {
    private FreezingAndBoilingPoints freezeAndBoiling;

    @Test
    public void test_isEthylAlchoolFreezing() {
        freezeAndBoiling = new FreezingAndBoilingPoints(-174);
        assertTrue(freezeAndBoiling.isEthylAlchoolFreezing());
    }

    @Test
    public void test_isEthylAlchoolBoiling() {
        freezeAndBoiling = new FreezingAndBoilingPoints(172);
        assertTrue(freezeAndBoiling.isEthylAlchoolBoiling());
    }

    @Test
    public void test_isOxygenFreezing() {
        freezeAndBoiling = new FreezingAndBoilingPoints(-400);
        assertTrue(freezeAndBoiling.isOxygenFreezing());
    }

    @Test
    public void test_isOxygenBoiling() {
        freezeAndBoiling = new FreezingAndBoilingPoints(-362);
        assertTrue(freezeAndBoiling.isOxygenFreezing());
    }

    @Test
    public void test_isWaterFreezing() {
        freezeAndBoiling = new FreezingAndBoilingPoints(10);
        assertTrue(freezeAndBoiling.isWaterFreezing());
    }

    @Test
    public void test_isWaterBoiling() {
        freezeAndBoiling = new FreezingAndBoilingPoints(213);
        assertTrue(freezeAndBoiling.isWaterBoiling());
    }
}