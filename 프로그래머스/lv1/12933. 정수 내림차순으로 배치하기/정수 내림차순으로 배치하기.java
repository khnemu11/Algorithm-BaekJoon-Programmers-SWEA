import java.util.*;
import java.util.stream.*;

class Solution {
    public long solution(long n) {
        String str = Arrays.stream(String.valueOf(n).split("")).sorted(Collections.reverseOrder()).collect(Collectors.joining());
        
        return Long.valueOf(str);
    }
}