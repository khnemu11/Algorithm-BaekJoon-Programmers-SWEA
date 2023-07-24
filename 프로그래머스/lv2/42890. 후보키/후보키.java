import java.util.*;
/*
    
    걸린시간 : 41분
    
    오래 걸린 이유 : 최소성에 대하여 제대로 알지 못함
    
    01이 후보키일 때 013은 후보키가 안되지만 023은 후보키가 될 수 있다.
*/
class Solution {
    Set<String> candidateKeys = new HashSet<>();
    boolean[] selected;
    int answer = 0;
    
    public int solution(String[][] relation) {
        candidateKeys = new HashSet<>();
        selected = new boolean[relation[0].length];
        
        //length길이의 후보키를 생성
        for(int length=1;length<=relation[0].length;length++){
            combination(0,length,0,relation);
        }
        
        return answer;
    }
    //dfs로 후보키를 생성하는 메소드
    public void combination(int depth, int length,int idx,String[][] relation){
        if(length == depth){
            Set<String> tuples = new HashSet<>();
            String preCandidateKey = "";
            
            //선택한 후보키 생성
            for(int i=0;i<selected.length;i++){
                if(selected[i]){
                    preCandidateKey +=i;
                }
            }
            
            //선택한 후보키가 이미 만들어진 후보키에 포함 되는지 확인(최소성 확인)
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
            
            //유일성 확인
            for(int i=0;i<relation.length;i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<selected.length;j++){
                    if(selected[j]){
                        sb.append(relation[i][j]+"|");
                    }
                }
                tuples.add(sb.toString());
            }
            
            //유일성과 최소성이 모두 맞으면 후보키 탐색 완료
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