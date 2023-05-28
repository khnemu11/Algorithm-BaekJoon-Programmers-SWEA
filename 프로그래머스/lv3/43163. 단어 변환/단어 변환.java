
import java.util.*;

class Solution {
    int minCnt=0;
    HashSet<String> visited = new HashSet<>();  //단어 방문 set
    HashMap<String,Boolean> canChageMap = new HashMap<>();  //단어로 바뀔 수 있는지 저장하는 map
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        minCnt = words.length+1;    //최대값 설정
        changeWord(begin,words,0,target);
        
        //바뀔 수 없다면
        if(minCnt == words.length+1){
            return 0;
        }
        
        return minCnt;
    }

    public void changeWord(String word,String [] words,int changeCnt,String target){
        //현재 최소 변환 횟수보다 많거나 같게 변화한 경우 더이상 탐색X
        if(changeCnt >= minCnt){
            return;
        }
        //목표 단어에 도달했을 때
        if(word.equals(target)){
            minCnt = Math.min(minCnt,changeCnt);
            return;
        }
        //모든 단어를 탐색하여 바뀔 수 있는지 판단
        for(int i=0;i<words.length;i++){
            //바뀔 수 있는지 판단 변수
            boolean change = false;
            //이미 방문했는가
            if(visited.contains(words[i])){
                continue;
            }
            //목표 단어로 바뀔 수 있는지 모를 때
            else if(canChageMap.get(word+words[i])==null){
                change = canChange(word,words[i]);
                canChageMap.put(word+words[i],change);
                canChageMap.put(words[i]+word,change);
            }
            //목표 단어로 바뀔 수 없을 때
            else if(canChageMap.get(word+words[i])==false){
                continue;
            }
            //목표 단어로 바뀔 수 있을 때
            else if(canChageMap.get(word+words[i])==true){
                change=true;
            }

            //바뀔 수 있다면 다음 단어로
            if(change){
                visited.add(words[i]); //방문  처리
                changeWord(words[i],words,changeCnt+1,target);
                visited.remove(words[i]);
            }
        }
    }
    //바뀔 수 있는지 문자가 다른 개수를 세서 판단해 주는 함수
    public boolean canChange(String str1, String str2){
        int differ=0;
        
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)!=str2.charAt(i)){
                differ++;
            }
        }
        
        if(differ==1){
            return true;
        }
        
        return false;
    }
}