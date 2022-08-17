import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> elements = new ArrayList<>();
        
        for(int value : arr){
            if(value % divisor == 0){
                elements.add(value);
            }   
        }
        if(elements.isEmpty()){
            elements.add(-1);
        }
        Collections.sort(elements);
        int[] answer = Arrays.stream(elements.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray(); 
        return answer;
    }
}