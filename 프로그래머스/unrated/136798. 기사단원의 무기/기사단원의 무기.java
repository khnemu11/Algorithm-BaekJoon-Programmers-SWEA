import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int total = 0;
        
        for(int n=1; n<=number;n++){
            Set<Integer> set = new HashSet<>();
            for(int i=1;i<=Math.sqrt(n);i++){
                if(n %i == 0){
                    set.add(i);
                    set.add(n/i);
                }
            }
            int sum = set.size() > limit ? power : set.size();
            total +=sum;
        }
        
        return total;
    }
}