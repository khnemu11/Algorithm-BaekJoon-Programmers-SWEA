import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        Set<Integer> phoneketmon = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            
            phoneketmon.add(nums[i]);
            
            if (phoneketmon.size() == nums.length / 2) {
                break;
            }
        }
        
        
        return phoneketmon.size();
    }
}