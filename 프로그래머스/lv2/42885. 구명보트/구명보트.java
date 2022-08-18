
import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
       	int answer = 0;
        int largest = people.length-1;
		int smallest=0;
		
		Arrays.sort(people);

		while(smallest<=largest) {
			if(people[smallest]+people[largest] <= limit) {
				smallest++;
			}
			answer++;
			largest--;
		}
        
        return answer;
    }
}