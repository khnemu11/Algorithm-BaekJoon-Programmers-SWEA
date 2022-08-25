import java.util.*;

class Solution {
	int hIndex;   
	
    public int solution(int[] citations) {
    	Arrays.sort(citations);

    	int curr =(int) Math.ceil((double)citations.length/2)-1;
    	hIndex = citations[curr]; 
    	
    	while(hIndex>0) {
    		long upperLine = Arrays.stream(citations).filter(x->x>=hIndex).count();
    		long underLine = Arrays.stream(citations).filter(x->x<=hIndex).count();
    		
    		if(hIndex<=upperLine && hIndex>=underLine) {
    			break;
    		}
    		
    		hIndex--;
    	}

        return hIndex;
    }
}