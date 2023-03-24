import java.util.*;
/*
    플이과정
    각 노드의 부모만 저장하여 부모가 루트노드(0)에 갈때까지 검색
    금액 10이하는 10%의 이익금을 부모에 전달할 수 없으므로 자식이 전부 가진다
    문자열을 인덱스화 시키기 위해 해쉬맵 사용
*/
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] income = new int[enroll.length];
        HashMap<String, Integer> map = new HashMap<>();
        int parent [] = new int[enroll.length+1];
    
        int idx = 0;
        map.put("-",idx++);
        
        for(int i=0;i<enroll.length;i++){
              map.put(enroll[i],idx++);
        } 
        
        for(int i=0;i<referral.length;i++){
             parent[map.get(enroll[i])] = map.get(referral[i]);
        } 
        
        for(int i=0;i<seller.length;i++){
            int curr = map.get(seller[i]);
            int earn = amount[i]*100;
            while(curr!=0){
                if(earn< 10){
                    income[curr-1] += earn;
                    break;
                }
                income[curr-1] +=earn - (int)(earn *0.1) ;
                earn = (int)(earn *0.1);
                curr = parent[curr];
            }
        }
    
        return income;
    }
    
}
