import static org.junit.Assert.*;

import org.junit.Test;

import javax.swing.plaf.PanelUI;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
        /* ANS: testError only checks if shift and generate methods can be called on the seed,
        it does not check if the methods are producing nonsensical outputs. Right way is to
        print out the outputs, so we know if the method is running as we wanted.*/
    }

    @Test
    public void testCase1() {
        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 7);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.shift();
            System.out.print(j);
        }

    }

    @Test
    public void testCase2() {
        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 7);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.generate(3);
            System.out.println(j);
        }

    }

    @Test
    public void myTest1() {
        // test what happens when user pass in invalid arguments
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        for (int i = 0; i < 7; i++) {
            int j = r.shift();
            System.out.println(j);
        };
    }

    @Test
    public void myTest2() {
        // test index out of bound
        ILFShiftRegister r = getRegister(-2, 1);
    }

    @Test
    public void myTest3() {
        // test what is seed is null
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = null;
        r.setSeed(seed);  // well guess java throw e for me haha
    }

    @Test
    public void myTest4() {
        // test if digit not 0/1
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = {0, 2, 1, 0};
        r.setSeed(seed);
    }
}
