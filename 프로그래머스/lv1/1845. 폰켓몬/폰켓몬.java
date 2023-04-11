import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> pocketmons = new HashSet<>();
        
        for(int num : nums){
            pocketmons.add(num);
        }

        int answer = nums.length/2 < pocketmons.size() ? nums.length/2 : pocketmons.size() ;
        return answer;
    }
}