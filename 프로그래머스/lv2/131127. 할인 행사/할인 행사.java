
/*
    1~10일 2~11일 ... -> 슬라이딩 윈도우
    시간복잡도 = 10 * 100,1000 < 1d억
    
    걸린시간 : 18분
*/


import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        HashMap<String,Integer> map = new HashMap<>();
        int answer=0;
        
        for(int i=0;i+9<discount.length;i++){
            if(i==0){
                for(int j=0;j<10;j++){
                map.put(discount[j],map.getOrDefault(discount[j],0)+1);
                }
            }else{
                map.put(discount[i-1],map.getOrDefault(discount[i-1],0)-1);
                map.put(discount[i+9],map.getOrDefault(discount[i+9],0)+1);
            }
            
            boolean valid=true;
            
            for(int j=0;j<number.length;j++){
                if(map.get(want[j])==null||map.get(want[j]) != number[j]){
                    valid=false;
                    break;
                }
            }
            
            if(valid){
                answer++;
            }
        }
        return answer;
    }
}