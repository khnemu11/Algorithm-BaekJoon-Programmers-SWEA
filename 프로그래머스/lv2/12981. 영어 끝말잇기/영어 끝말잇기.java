import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0,0};
        Set<String> set = new HashSet<>();
        int peopleNo = 1;
        int seq = 1;
        
        for(int i=0;i<words.length;i++){
            if(set.contains(words[i])){
                answer = new int[]{peopleNo,seq};
                break;
            }
            else if(words[i].length()==1){
                answer = new int[]{peopleNo,seq};
                break;
            }else if(i > 0 && words[i-1].charAt(words[i-1].length()-1)!= words[i].charAt(0)){
                answer = new int[]{peopleNo,seq};
                break;
            }
            set.add(words[i]);
            peopleNo++;
            
            if(peopleNo > n){
                peopleNo=1;
                seq++;
            }
        }
        
        return answer;
    }
}