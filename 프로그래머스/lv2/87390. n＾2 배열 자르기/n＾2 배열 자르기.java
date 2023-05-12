/*
    2차원 배열 -> 1차원 배열로 변경
    (인덱스 / 길이, 인덱스 % 길이) 
    이때 행의 값만큼 열의 값이 행 값으로 변경된다.
*/
import java.util.*;
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = {};
        int map[] = new int[(int)(right-left)+1];
        long length = (long)n*n;
        
        for(long i = left; i<=right;i++){
            long val1 = i/n+1;
            long val2 = i%n+1;
            int idx = (int)(i-left);
            map[idx] = (int)Math.max(val1,val2);
        }
        
        return map;
    }
}