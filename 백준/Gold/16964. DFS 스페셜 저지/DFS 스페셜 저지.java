import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {
	static List<Integer> graph[];
	static boolean[] visited;
	static int idx;
	static int[] target;
	
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int N = Integer.parseInt(br.readLine());
       graph = new ArrayList[N+1];
       
       for(int i=0;i<=N;i++) {
    	   graph[i] = new ArrayList<>();
       }
       
       visited = new boolean[N+1];
       
       for(int i=0;i<N-1;i++) {
    	  StringTokenizer st = new StringTokenizer(br.readLine());
    	  int start = Integer.parseInt(st.nextToken());
    	  int end = Integer.parseInt(st.nextToken());
    	  
    	  graph[start].add(end);
    	  graph[end].add(start);
       }

       target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       idx = 1;
       
       if(target[0] != 1) {
    	   System.out.println(0);
       }else if(validGraph(1)) {
    	   System.out.println(1);
       }else {
    	   System.out.println(0);
       }
	}
	
	public static boolean validGraph(int curr) {
		visited[curr] = true;
		Set<Integer> nextSet = new HashSet<>();
		
		for(int next : graph[curr]) {
			if(visited[next]) {
				continue;
			}
			nextSet.add(next);
		}
		
		while(nextSet.size() > 0) {
			if(!nextSet.contains(target[idx])) {
				return false;
			}
			
			nextSet.remove(target[idx]);
			visited[target[idx]] = true;
			
			if(!validGraph(target[idx++])) {
				return false;
			}
		}
		
		return true;
	}
}