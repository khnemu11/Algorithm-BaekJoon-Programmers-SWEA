
import java.util.*;

class Solution {
    int minCnt=0;
    HashSet<String> visited = new HashSet<>();
    HashMap<String,Boolean> canChageMap = new HashMap<>();
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        minCnt = words.length+1;
        changeWord(begin,words,0,target);
        
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
        if(word.equals(target)){
            minCnt = Math.min(minCnt,changeCnt);
            return;
        }
        for(int i=0;i<words.length;i++){
             boolean change = false;
            if(visited.contains(words[i])){
                continue;
            }else if(canChageMap.get(word+words[i])==null){
                change = canChange(word,words[i]);
                canChageMap.put(word+words[i],change);
                canChageMap.put(words[i]+word,change);
            }
            else if(canChageMap.get(word+words[i])==false){
                continue;
            }else if(canChageMap.get(word+words[i])==true){
                change=true;
            }

            if(change){
                visited.add(words[i]);
                changeWord(words[i],words,changeCnt+1,target);
                visited.remove(words[i]);
            }
        }
    }
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