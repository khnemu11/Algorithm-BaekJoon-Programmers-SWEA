import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        /*
        ArrayList<Integer> elements = new ArrayList<>();
        
        for(int value : arr){
            if(value % divisor == 0){
                elements.add(value);
            }   
        }
        if(elements.isEmpty()){
            elements.add(-1);
        }
        Collections.sort(elements);*/
        int[] answer = Arrays.stream(arr).filter(e -> e % divisor == 0).toArray();
        if(answer.length==0){
            answer = new int []{-1};
        }
        else {
            Arrays.sort(answer);   
        }
        
        return answer;
    }
}