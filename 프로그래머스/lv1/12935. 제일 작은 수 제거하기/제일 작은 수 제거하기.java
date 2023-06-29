import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();
        int removedArr[] = Arrays.stream(arr).filter(x->x>min).toArray();
        
        if(removedArr.length==0){
            return new int[]{-1};
        }
        
        return removedArr;
    }
}