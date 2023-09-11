import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int b_idx =0; 
        
        for(int i=0;i<A.length;i++){
            // System.out.println(i+" : "+A[i]);
            while(b_idx <B.length){
                // System.out.println(A[i] +" vs "+B[b_idx]);
                if(A[i] < B[b_idx]){
                    answer++;
                    b_idx++;
                    break;
                } 
                b_idx++;
            }
        }
        
        return answer;
    }
}