import java.util.*;
/*
    1,000,000 => 최대한 한번만 반복문 나오도록
    
    2부분으로 자르고 한칸씩 땡겨진다.
    => 슬라이드 윈도우
    
    6분 29초
*/
class Solution {
    public int solution(int[] topping) {
        Map<Integer,Integer> left = new HashMap<>();
        Map<Integer,Integer> right = new HashMap<>();
        int answer = 0;
        
        for(int i=0;i<topping.length;i++){
            //왼쪽 1개, 나머지 오른쪽 모두 넣기
            if(i==0){
                left.put(topping[0],1);
                for(int j=1;j<topping.length;j++){
                    right.put(topping[j],right.getOrDefault(topping[j],0)+1);
                }
            }
            //왼쪽에 현재 케이크 넣고 오른쪽에 현재 케이크 빼기
            else{
                left.put(topping[i],left.getOrDefault(topping[i],0)+1);
                right.put(topping[i],right.get(topping[i])-1);
                
                if(right.get(topping[i])==0){
                    right.remove(topping[i]);
                }
                
            }
            //종류의 개수가 같으면 공평한 개수 증가
            if(left.keySet().size() == right.keySet().size()){
                answer++;
            }
        }
        
        return answer;
    }
}