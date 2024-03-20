import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	final static int MAX_DAY = 1_000;
	
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int N = Integer.parseInt(br.readLine());
      
       int[] max = new int[MAX_DAY+1];
       int[] days = new int[MAX_DAY+1];
       
       for(int i=1;i<days.length;i++) {
    	   days[i] = i;
       }
       
       PriorityQueue<Homework> pq = new PriorityQueue<>();
       
       for(int i=0;i<N;i++) {
    	   StringTokenizer st = new StringTokenizer(br.readLine());
    	   pq.add(new Homework(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
       }
       
       while(!pq.isEmpty()) {
    	   Homework homework = pq.poll();
    	   
    	   int day = days[homework.day];
    	   
    	   while(day > 0){
    		   if(max[day] < homework.score) {
    			   max[day] = homework.score;
    			   days[homework.day]--;
    			   break;
    		   }
    		   day--;
    	   }
       }
       
       System.out.println(Arrays.stream(max).sum());
	}
}
class Homework implements Comparable<Homework>{
	int day;
	int score;
	
	public Homework(int day, int score) {
		this.day = day;
		this.score = score;
	}
	
	@Override
	public int compareTo(Homework o) {
		if(this.score == o.score) {
			return o.day - this.day;
		}
		
		return o.score - this.score;
	}
}