import java.util.*;


class Solution {
    boolean allVisited[];
    int cnt=0;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        String regexs[] = convertRegex(banned_id);
        allVisited=new boolean[(1<<user_id.length)];
        
        match(user_id,regexs,0,0);
        
        return cnt;
    }
    
    public String[] convertRegex(String[] banned_id){
        String allRegex = "[a-z0-9]";
        String regexs[]= new String[banned_id.length];
        
        for(int i=0;i< banned_id.length;i++){
            regexs[i] = banned_id[i].replace("*",allRegex);
        }
        
        return regexs;
    }
    public void match(String user_id[], String regexs[], int idx,int selected){
        if(idx >=regexs.length){
            if(allVisited[selected]){
                return;
            }
            // System.out.println("final : "+Integer.toBinaryString(selected));
            allVisited[selected]=true;
            cnt++;
            return;
        }else{
            for(int i=0;i<user_id.length;i++){
                if(((selected>>i) & 1) == 1 || !(user_id[i].matches(regexs[idx]))){
                    continue;
                }
                // System.out.println(Integer.toBinaryString(selected | (1<<i)));
                match(user_id,regexs,idx+1, selected | (1<<i));
            }
        }
    }
}