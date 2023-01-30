/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        // check for empty array
        int size = dataArray.length;
        if (size == 0) {
            return 0; // Invalid input
        } else if (size == 1) {
            return dataArray[0]; // 1 elem only
        } else if (size == 2){
            return Math.max(dataArray[0], dataArray[1]); // 2 elems only
        } else {
            // more than 2 elem, split into 2 cases
            // case 1: decrease; case 2: increase
            if (dataArray[1] < dataArray[0]) {
                if (dataArray[0] > dataArray[size -1]) {
                    return dataArray[0]; // decreasing all the way or decrease then increase but last elem smaller than first
                } else {
                    return dataArray[size - 1]; // v-shaped and last elem more than first
                }
            } else {
                int max = 0; // store the temp max
                int begin = 0;
                int end = size - 1;
                while (begin < end) {
                    int mid = (int) Math.floor( (begin + end) / 2);
                    if (dataArray[mid] > dataArray[mid + 1]) {
                        end = mid;
                        max = dataArray[mid];
                    } else {
                        begin = mid;
                        max = dataArray[mid + 1];
                    }
                }
                return max; // for increasing all the way
            }
        }
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
