import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 523. Continuous Subarray Sum
 * https://leetcode.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {


    /**
     * Runtime: 2 ms, faster than 99.42% of Java online submissions.
     * Memory Usage: 39.2 MB, less than 99.38% of Java online submissions.
     */
    static boolean checkSubarraySum(int[] nums, int k) {
        
        // **** initialization ****
        Map<Integer, Integer> hm    = new HashMap<Integer, Integer>();
        hm.put(0, -1);

        int sum                     = 0;

        // ???? ????
        int[] sums                  = new int[nums.length];

        // **** loop computing sums [0, n - 1] ****
        for (int i = 0; i < nums.length; i++) {

            // ???? update running sum ????
            sums[i] = nums[i];
            if (i > 0)
                sums[i] += sums[i - 1];
            if (k != 0)
                sums[i] %= k;

            // ???? ????
            System.out.println("checkSubarraySum <<<     sums: " + Arrays.toString(sums));

            // **** update sum ****
            sum += nums[i];

            // ???? ????
            System.out.println( "checkSubarraySum <<< i: " + i + " sum: " + sum);

            // **** remainder ****
            if (k != 0) {
                sum %= k;
            }

            // ???? ????
            System.out.println( "checkSubarraySum <<< i: " + i + " sum: " + sum);

            // **** check if we have this sum ****
            if (hm.containsKey(sum)) {

                // ???? ????
                System.out.println("checkSubarraySum <<<      sum: " + sum);
                System.out.println("checkSubarraySum <<<  sums[i]: " + sums[i]);

                // **** the sum is larger than 1; sub array was found  ****
                if (i - hm.get(sum) > 1) {
                    return true;
                }
            }

            // **** add this sum to the hash map ****
            hm.putIfAbsent(sum, i);

            // ???? ????
            System.out.println("checkSubarraySum <<<       hm: " + hm.toString());
        }

        // **** sub array not found ****
        return false;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // **** read array contents ****
        String[] strs = reader.readLine().trim().split(",");

        // **** read k ****
        int k = Integer.parseInt(reader.readLine().trim());

        // **** close reader ****
        reader.close();

        // **** create and populate array of numbers ****
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }

        // ???? ????
        System.out.println("main <<< nums: " + Arrays.toString(nums));
        System.out.println("main <<<    k: " + k);

        // **** ****
        System.out.println("main <<< checkSubarraySum: " + checkSubarraySum(nums, k));
    }
}