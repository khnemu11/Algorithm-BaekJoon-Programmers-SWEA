import java.util.*;
/*
    연속 수열 = 2배길이의 수열 
    5:05
*/
class Solution {
    public int solution(int[] elements) {
        int [] expendedElements = new int[elements.length*2 - 1];
        Set<Integer> duplicatedSum = new HashSet<>();
        
        //2배 수열 생성
        for(int i=0;i<expendedElements.length;i++){ 
            expendedElements[i] = elements[i%elements.length];
            duplicatedSum.add(expendedElements[i]);
        }
        
        int[] sums  = new int[expendedElements.length];
        sums[0] = expendedElements[0];
        
        //2배 누적합 생성
        for(int i=1;i<sums.length;i++){
            sums[i] = sums[i-1] + expendedElements[i];
        }
        
        //2배 구간합 계산
        for(int i=1;i<elements.length;i++){
            for(int j=0;j+i<expendedElements.length;j++){
                duplicatedSum.add(sums[j+i]-sums[j]+expendedElements[j]);
            }   
        }
        
        return duplicatedSum.size();
    }
}