import java.util.*;

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
                // System.out.println(curr+" get : "+earn);
                // System.out.println(curr+" earn : "+(earn - (int)(earn *0.1)));
                // System.out.println(curr+" give to  "+parent[curr]+" "+((int)(earn *0.1)));
                income[curr-1] +=earn - (int)(earn *0.1) ;
                earn = (int)(earn *0.1);
                curr = parent[curr];
            }
        }
    
        return income;
    }
    
}