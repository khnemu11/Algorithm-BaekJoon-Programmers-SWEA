import java.util.*;

class Solution {
    public int[] solution(long n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        
        return Arrays.stream(sb.reverse().toString().split("")).mapToInt(x->Integer.valueOf(x)).toArray();
    }
}