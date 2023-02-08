import java.util.*;

class Solution {
    int max_profit=0;
    int emoticons_sign_cnt=0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int probs[] = {40,30,20,10};
        
        pick(0,probs,new int[emoticons.length],users,emoticons);
        int answer [] = {emoticons_sign_cnt,max_profit};
        
        return answer;
    }
    
    public void pick(int idx,int[] probs,int[] selected,int [][]users,int[] emoticons){
        if(idx == emoticons.length){
            calculateSignUp(selected,users,emoticons);
        }
        else{
            for(int i=0;i<probs.length;i++){
                selected[idx]=probs[i];
                pick(idx+1,probs,selected,users,emoticons);
            }
        }
    }
    
    public void calculateSignUp(int discounts[], int users [][],int[] emoticons){
        int cnt = 0;
        int total = 0;
        
        for(int i=0;i<users.length;i++){
            int sum = 0;
            boolean isSign=false;
            
            for(int j=0;j<emoticons.length;j++){
                if(users[i][0] > discounts[j]){
                    continue;
                }    
                
                sum+= (int)(((100- discounts[j]) * emoticons[j])/100.0);
                
                if(sum >= users[i][1]){
                    sum = 0;
                    isSign=true;
                    break;
                }
            }
            
            if(isSign){
                cnt++;
            }else{
                total+=sum;
            }
        }
             
        if(emoticons_sign_cnt < cnt){
            emoticons_sign_cnt = cnt;
            max_profit = total;
        }else if(emoticons_sign_cnt == cnt){
            max_profit = Math.max(max_profit,total);
        }
    }
}