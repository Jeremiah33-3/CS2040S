///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    int[] shiftRegister;
    int tapBit;
    int size;
    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // check if user pass in illegal values
        if (size < 0 || tap < 0 || tap > size) {
            System.out.println("Illegal arguments! >_<");
        } else {
            // update size of the register
            shiftRegister = new int[size];
            // storing tap bit and size
            tapBit = tap;
            this.size = size;
        }
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     */
    @Override
    public void setSeed(int[] seed) {
        // check if seed is larger or smaller than size or seed is null
        if (seed.length < size || seed.length > size || seed == null) {
            System.out.println("Invalid seed :(");
        } else {
            // update the shiftRegister to initial seed
            for (int j : seed) {
                if (j != 0 && j != 1) {
                    System.out.println("Error: contains digit not '0' or '1'. ");
                    return;
                }
            }
            shiftRegister = seed;
        }
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // mirror image the shifting
        int feedback = shiftRegister[size - 1] ^ shiftRegister[tapBit];
        // shift right
        for (int i = shiftRegister.length - 1; i > 0; i--) {
            shiftRegister[i] = shiftRegister[i - 1];
        }
        shiftRegister[0] = feedback;
        return shiftRegister[0];
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // array of bits
        int[] extract = new int[k];
        for (int i = 0; i < k; i++) {
            extract[i] = this.shift();
        }
        return toDecimal(extract);
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // first declare a result var to store decimal num
        int result = 0;
        // array index must be tweaked to fit least significant pos
        // raise 2 to power position for each position
        for (int i = 0; i < array.length; i++) {
            result += (array[i] * Math.pow(2, array.length - i - 1));
        }
        return result;
    }
}
