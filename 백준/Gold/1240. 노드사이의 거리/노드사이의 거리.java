import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {	
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       	
       int[][] minDistance = new int[N+1][N+1];
       List<Node> graph[] = new ArrayList[N+1];
       
       for(int i=0;i<=N;i++) {
    	   graph[i] = new ArrayList<>();
    	   Arrays.fill(minDistance[i], Integer.MAX_VALUE);
       }
     
       for(int i=0;i<N-1;i++) {
    	   st = new StringTokenizer(br.readLine());
    	   int start = Integer.parseInt(st.nextToken());
    	   int end = Integer.parseInt(st.nextToken());
    	   int distance = Integer.parseInt(st.nextToken());
    	   
    	   graph[start].add(new Node(end,distance));
    	   graph[end].add(new Node(start,distance));
       }
       
       for(int start=1;start<=N;start++) {
    	   PriorityQueue<Node> pq = new PriorityQueue<>((x,y)->x.distance - y.distance);
    	   
    	   pq.add(new Node(start,0));
    	   minDistance[start][start] = 0;
    	   
    	   while(!pq.isEmpty()) {
    		   Node curr = pq.poll();
    		  
    		   if(minDistance[start][curr.seq] < curr.distance) {
    			   continue;
    		   }
    		   
    		   for(Node next : graph[curr.seq]) {
    			   if(minDistance[start][next.seq] <= curr.distance + next.distance) {
        			   continue;
        		   }
    			   
    			   minDistance[start][next.seq] = curr.distance + next.distance;
    			   pq.add(new Node(next.seq,minDistance[start][next.seq]));
    		   }
    	   }
       }
       
       for(int i=0;i<M;i++) {
    	   st = new StringTokenizer(br.readLine());
    	   
    	   int start = Integer.parseInt(st.nextToken());
    	   int end = Integer.parseInt(st.nextToken());
    	   
    	   System.out.println(minDistance[start][end]);
       }
	}
}
class Node{
	int seq;
	int distance;
	
	public Node(int seq, int distance) {
		this.seq = seq;
		this.distance = distance;
	}
}