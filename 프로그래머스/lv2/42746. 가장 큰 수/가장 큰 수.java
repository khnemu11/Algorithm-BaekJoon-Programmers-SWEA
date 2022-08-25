import java.util.*;
import java.util.stream.*;
class Solution {
    public String solution(int[] numbers) {
              String answer = "";
	       String[] input = Arrays.stream(numbers).boxed().map(String::valueOf).toArray(String[]::new);
	        
	        if(Arrays.stream(numbers).filter(x->x==0).count()==numbers.length) {
	        	answer="0";
	        }
	        else {
	        	Arrays.sort(input,new Comparator<String>() {
					//합쳤을 때 큰수를 기준으로 오름차순 정렬
	        		@Override
					public int compare(String o1, String o2) {
	        			
	        			return -(o1+o2).compareTo(o2+o1);
					}
		        });
	        	 answer= Arrays.stream(input).collect(Collectors.joining());
	        }

	        return answer;
    }
}