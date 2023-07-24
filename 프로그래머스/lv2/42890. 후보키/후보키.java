import java.util.*;

class Solution {
    Set<String> candidateKeys = new HashSet<>();
    boolean[] selected;
    int answer = 0;
    
    public int solution(String[][] relation) {
        candidateKeys = new HashSet<>();
        selected = new boolean[relation[0].length];
        
        for(int length=1;length<=relation[0].length;length++){
            combination(0,length,0,relation);
        }
        
        return answer;
    }
    public void combination(int depth, int length,int idx,String[][] relation){
        if(length == depth){
            Set<String> tuples = new HashSet<>();
            String preCandidateKey = "";
            
            for(int i=0;i<selected.length;i++){
                if(selected[i]){
                    preCandidateKey +=i;
                }
            }
            
            for(String key : candidateKeys){
                int containCnt = 0;
                for(int i=0;i<key.length();i++){
                    if(preCandidateKey.contains(String.valueOf(key.charAt(i)))){
                        containCnt++;   
                    }
                }
                if(containCnt == key.length()){
                    return;
                }
            }
            
            for(int i=0;i<relation.length;i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<selected.length;j++){
                    if(selected[j]){
                        sb.append(relation[i][j]+"|");
                    }
                }
                tuples.add(sb.toString());
            }
            
            if(tuples.size() == relation.length){
                candidateKeys.add(preCandidateKey);
                answer++;
            }
    
            return ;
        }
        for(int i=idx;i<relation[0].length;i++){
            selected[i]=true;
            combination(depth+1,length,i+1,relation);
            selected[i]=false;
        }
    }
}