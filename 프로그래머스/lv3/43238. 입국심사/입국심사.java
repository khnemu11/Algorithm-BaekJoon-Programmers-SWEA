import java.util.*;
/*
    풀이과정
    
    시간의 범위 : 1~ 1,000,000,000 
    
    1억이 넘으므로 logn의 알고리즘 필요
    => 이분탐색
    
    심사관이 100,000이므로 O(100,000 * log(1,000,000,000)) < 1억
*/
class Solution {
    public long solution(int n, int[] times) {
        long l = 0; //최저 시간
        long r = Long.MAX_VALUE; //최고 시간
        
        while(l<r){
            long mid = (l+r)/2; //현재 시간
            long count = 0; //검사한 사람 수
            
            //현재 시간으로 검사할 시 검사할 수 있는 사람 구하기
            for(int time : times){
                count += mid / time;
                //이미 목표 인원을 달성했으면 오버플로우 방지를 위해 반복문 종료
                if(count >=n){
                    break;
                }        
            }            
            
            //현재 시간으로 목표 인원을 구할 수 있는경우
            if(count >= n){
                r = mid;
            }
            //없는경우
            else{
                l = mid + 1;
            }
        }
        
        return r;
    }
}
