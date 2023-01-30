/**
 * Contains static routines for solving the problem of balancing m jobs on p processors
 * with the constraint that each processor can only perform consecutive jobs.
 */
public class LoadBalancing {

    /**
     * Checks if it is possible to assign the specified jobs to the specified number of processors such that no
     * processor's load is higher than the specified query load.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param queryLoad the maximum load allowed for any processor
     * @param p the number of processors
     * @return true iff it is possible to assign the jobs to p processors so that no processor has more than queryLoad load.
     */
    public static boolean isFeasibleLoad(int[] jobSizes, int queryLoad, int p) {
        // assign jobs to p so long as total load < queryLoad; then go to the next p (pcount - 1)
        // if p == 0 or jobSize[i] > queryLoad (impossible to take) break --> return int remains == 0
        if (jobSizes.length == 0 || p == 0 || queryLoad == 0) {
            return false;
        } else {
            int currentLoad = 0;
            int pCount = p;
            int numjobs = jobSizes.length;
            int remains = numjobs;
            for (int i = 0; i < numjobs; i++) {
                if (jobSizes[i] > queryLoad || pCount == 0) {
                    break;
                } else if (currentLoad + jobSizes[i] <= queryLoad) {
                    currentLoad += jobSizes[i];
                    remains--;
                } else {
                    if (pCount == 1) {
                        break;
                    } else {
                        currentLoad = jobSizes[i];
                        remains--;
                        pCount--;
                    }
                }
            }
            return (remains == 0);
        }
    }

    /**
     * Returns the minimum achievable load given the specified jobs and number of processors.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param p the number of processors
     * @return the maximum load for a job assignment that minimizes the maximum load
     */
    public static int findLoad(int[] jobSizes, int p) {
        if (jobSizes.length == 0 || p == 0) {
            return -1;
        }
        int numJobs = jobSizes.length;
        int minLoad = 0;
        while (!(isFeasibleLoad(jobSizes, minLoad, p))) {
            minLoad++;
        }
        return minLoad;
    }

    // These are some arbitrary testcases.
    public static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, 100, 80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83},
            {7}
    };

    /**
     * Some simple tests for the findLoad routine.
     */
    public static void main(String[] args) {
        /*for (int p = 1; p < 30; p++) {
            System.out.println("Processors: " + p);
            for (int[] testCase : testCases) {
                System.out.println(findLoad(testCase, p));
            }
        }*/
        System.out.println(findLoad(new int[]{},5));
    }
}
