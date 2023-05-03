import java.util.*;

class Solution {
    public String solution(String s) {
        String [] input = s.split(" ");
        
        int num[] = Arrays.stream(input).mapToInt(x->Integer.valueOf(x)).toArray();
        int max = Arrays.stream(num).max().getAsInt();
        int min = Arrays.stream(num).min().getAsInt();
        
        return min+" "+max;
    }
}