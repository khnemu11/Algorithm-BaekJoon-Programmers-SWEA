import java.util.*;
/*
    1 2 4
    4 4 5
    (4*4) + (2*4) + (1*5)  = 29
    
    정렬하고 양 끝을 곱하고 더하는 것이 최소
*/
class Solution{
    public int solution(int []A, int []B){
        int sum = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=0;i<A.length;i++){
            sum += A[i] * B[B.length-i-1];
        }
        

        return sum;
    }
}