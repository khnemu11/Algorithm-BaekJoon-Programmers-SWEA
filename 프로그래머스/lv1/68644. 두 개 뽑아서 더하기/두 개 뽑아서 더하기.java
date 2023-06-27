import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> set = new TreeSet<>();
        
        for(int i=0;i<numbers.length;i++){
            for(int j=i+1;j<numbers.length;j++){
                set.add(numbers[i]+ numbers[j]);
            }   
        }
        
        return Arrays.stream(set.toArray(new Integer[0])).mapToInt(x->Integer.valueOf(x)).toArray();
    }
}