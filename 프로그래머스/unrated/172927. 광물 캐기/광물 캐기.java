/*

    피로도 = 곡괭이 X 광물 => 2차원 배열로 표현
    조합으로 해결시 시간복잡도 = 곡괭이의 개수 ^ 광물 선택의 횟수 = 3^(50/5) = 3^10 < 1억
    => 조합으로 해결 가능
    
    걸린시간 : 20분
*/

import java.util.*;
class Solution {
    int min = Integer.MAX_VALUE;
    int fatique[][] = {{1,1,1},{5,1,1},{25,5,1}};   //[곡괭이][광물]의 피로도 배열
    HashMap<String,Integer> AtrributeIndex = new HashMap<>(); //재료 속성->인덱스로 변환하는 맵
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        //재료 속성을 인덱스로 변환
        AtrributeIndex.put("diamond",0);
        AtrributeIndex.put("iron",1);
        AtrributeIndex.put("stone",2);
        
        select(0,new int[3],picks,minerals,0);
        
        return min;
    }
    public void select(int idx, int used[],int picks[],String[] minerals,int cost){
        //모든 미네랄을 캔 경우
        if(idx >= minerals.length){
            min = Math.min(cost,min);
        }
        //현재 비용이 최소값보다 작은경우 더이상 탐색할 필요 없음
        else if(cost >= min){
            return ;
        }else{
            //사용할 곡괭이가 있는지 판단하는 변수
            boolean canUse=false;
            //곡괭이를 하나씩 선택
            for(int i=0;i<used.length;i++){
                //이미 다 사용한 경우
                if(used[i] >= picks[i]){
                    continue;
                }
                canUse=true;
                //현재 곡괭이로 소비된 피로도
                int currFatigue=0;
                //5개의 광물 채굴
                for(int mineralsIdx=0;mineralsIdx<5;mineralsIdx++){
                    //모든 광물 채굴할 시
                    if(idx + mineralsIdx>=minerals.length){
                        break;
                    }
                    currFatigue+=fatique[i][AtrributeIndex.get(minerals[idx + mineralsIdx])];
                }
                //곡괭이 사용
                used[i]++;
                //다음 광물로
                select(idx+5, used, picks, minerals,cost+currFatigue);
                //곡괭이 돌려놓음
                used[i]--;
            }
            //사용할 곡괭이가 없는경우 현재 최솟값 저장
            if(!canUse){
                min = Math.min(cost,min);
                return;
            }
        }
    }
}
