import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int answer[][] = new int[3][answers.length];
        int count[] = new int[3];
                
        for(int i=0;i<answers.length;i++){
            answer[0][i] = i%5+1;
            if(answer[0][i] == answers[i]){
                count[0]++;
            }
        }
        
        int vals[] = {1,3,4,5};
        int idx=0;
        for(int i=0;i<answers.length;i++){
            if(i%2==0){
                answer[1][i]=2;
            }else{
                answer[1][i]=vals[idx];
                idx = (idx+1)%4;
            }
            
             if(answer[1][i] == answers[i]){
                count[1]++;
            }
        }
        
        idx=0;
        int thirdVals[] = {3,1,2,4,5};
        for(int i=0;i<answers.length;i++){
            if(i>0 && i%2==0){
                idx = (idx +1)%5;
            }
            answer[2][i] = thirdVals[idx];  
            if(answer[2][i] == answers[i]){
                count[2]++;
            }
        }
        
        int max = Arrays.stream(count).max().getAsInt();
        // System.out.println(Arrays.toString(count));
        // System.out.println(Arrays.deepToString(answer));
        if(max==0){
            return new int[0];
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<count.length;i++){
            if(count[i]==max){
                list.add(i+1);
            }
        }
        
        int result[] = new int[list.size()];
        
        for(int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
}