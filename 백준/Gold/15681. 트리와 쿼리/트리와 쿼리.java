import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 15: 54
 * 
 * 
 * */

public class Main {
	static int[] count;
	static boolean[] visited;
	static List<Integer>[] tree;
	
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       
       int N = Integer.parseInt(st.nextToken());
       int R = Integer.parseInt(st.nextToken());
       int Q = Integer.parseInt(st.nextToken());
       
       visited = new boolean[N+1];
       count = new int[N+1];
       tree = new ArrayList[N+1];
       
       for(int i=0;i<=N;i++) {
    	   tree[i] = new ArrayList<>();
       }
       
       for(int i=0;i<N-1;i++) {
    	   st = new StringTokenizer(br.readLine());
    	   
    	   int start = Integer.parseInt(st.nextToken());
    	   int end = Integer.parseInt(st.nextToken());
    	   
    	   tree[start].add(end);
    	   tree[end].add(start);
       }
       
       getVertexCount(R);
       
       for(int i=0;i<Q;i++) {
    	   int q = Integer.parseInt(br.readLine());
    	   
    	   System.out.println(getVertexCount(q));
       }
	}
	
	public static int getVertexCount(int n) {
		if(visited[n]) {
			return count[n];
		}
		
		visited[n] = true;
		count[n] = 1; //자기 자신 포함
		
		//자식 노드 개수 탐색
		for(int next : tree[n]) {
			if(visited[next]) {
				continue;
			}
			
			count[n] += getVertexCount(next);
		}
		return count[n];
	}
}