import java.util.*;

/*
    A와 B가 오름차순 정렬일 때 
    B의 i번째 수가 A의 j번째수 보다 크면 B의 i+1번째 수는 A의 j번째수보다 무조건 크다
    => A의 j+1번째 수를 볼 때는 B의 i+1부터 확인해 i번째 까지의 수는 재확인하지 않도록 한다.
    => B와 A의 요소를 한번 씩 순회하므로 O(N) = N(A의 길이) + N(B의 길이) = 2N = N
*/
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        //숫자가 작은것 부터 큰 순서로 서로 비교하기 위해 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        
        int b_idx =0; 
        
        //1) A[i] < B[b_idx] : B[b_idx+1]은 A[0] ~ A[i]보다 무조건 크므로 승점을 1개 얻는다.
        //2) A[i] >= B[b_idx] : B[b_idx]은 A[i] ~ A[A의 길이 -1]보다 무조건 작으므로 승점을 얻지 못한다.
        for(int i=0;i<A.length;i++){
            while(b_idx <B.length){
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