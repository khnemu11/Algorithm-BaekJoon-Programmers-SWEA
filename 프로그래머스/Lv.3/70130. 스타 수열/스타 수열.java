import java.util.*;

/*
    조건 1) 짝수 길이어야 한다
    -> 인덱스를 2개씩 올린다.
    조건 2) 바로 오른쪽의 값과 현재 값이 달라야 한다.
    -> 조건문 처리
    조건 3) 모두가 같은 수 1개를 가지고 있어야 한다.
    -> 집합끼리 교집합을 하는 것은 시간복잡도 상 불가
    -> 그렇다면 가능한 모든 수를 반복문으로 돌려 해당 숫자를 가지고 있는지 판단한다.
    
    안좋은 문제...
    시간복잡도가 N^2이라 효율성을 따지면 무조건 터지는 문제
*/

class Solution {
    public int solution(int[] a) {
        int[] count = new int[a.length];
        int max = 0;
        
        for(int val : a){
            count[val]++;
        }
        
        for(int intersection=0;intersection<count.length;intersection++){
            if(count[intersection] <= max/2){
                continue;
            }
            int length = 0;
            
            for(int i=0;i<a.length-1;i++){
                if(a[i] != intersection && a[i+1] != intersection){
                    continue;
                }
                if(a[i] == a[i+1]){
                    continue;
                }
                
                length+=2;
                i++;
            }
            
            max = Math.max(max,length);
            
        }
        
        return max;
    }
}