import java.util.*;
class Solution {
    int min = Integer.MAX_VALUE;
    int fatique[][] = {{1,1,1},{5,1,1},{25,5,1}};
    HashMap<String,Integer> AtrributeIndex = new HashMap<>();
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        AtrributeIndex.put("diamond",0);
        AtrributeIndex.put("iron",1);
        AtrributeIndex.put("stone",2);
        
        select(0,new int[3],picks,minerals,0);
        
        return min;
    }
    public void select(int idx, int used[],int picks[],String[] minerals,int cost){
        if(idx >= minerals.length){
            // System.out.println(cost);
            min = Math.min(cost,min);
        }else if(cost >= min){
            return ;
        }else{
            // System.out.println(Arrays.toString(used)+ " 비용 : "+cost+" 다음 선택 : "+idx );
            boolean canUse=false;
            for(int i=0;i<used.length;i++){
                if(used[i] >= picks[i]){
                    continue;
                }
                canUse=true;
                // System.out.println(i+" 선택 ");
                int currFatigue=0;
                for(int mineralsIdx=0;mineralsIdx<5;mineralsIdx++){
                    if(idx + mineralsIdx>=minerals.length){
                        // System.out.println(idx+mineralsIdx+" 벗어남");
                        break;
                    }
                    // System.out.println(i+"로 "+(idx + mineralsIdx)+" 번째 "+minerals[idx + mineralsIdx]+"제거");
                    currFatigue+=fatique[i][AtrributeIndex.get(minerals[idx + mineralsIdx])];
                }
                used[i]++;
                select(idx+5, used, picks, minerals,cost+currFatigue);
                used[i]--;
            }
            
            if(!canUse){
                min = Math.min(cost,min);
                return;
            }
        }
    }
}