import java.util.*;

public class Solution {
    public int solution(int n) {
        return Arrays.stream(Arrays.stream(String.valueOf(n).split("")).mapToInt(x->Integer.valueOf(x)).toArray()).sum();
    }
}