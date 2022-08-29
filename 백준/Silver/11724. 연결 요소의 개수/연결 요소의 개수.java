import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>>graph;
	static boolean[]visited;
	
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int vertices = Integer.valueOf(st.nextToken());
		int edges = Integer.valueOf(st.nextToken());
		graph = new ArrayList<>();
		visited=new boolean[vertices+1];
		int count=0;
		
		//그래프에 정점의 개수만큼 추가
		
		for(int i=0;i<=vertices;i++) {
			graph.add(new ArrayList<>());
		}
		
		//각 정점에 이어져있는 노드 추가
		
		for(int i=0;i<edges;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		//System.out.println(" >> "+graph.toString());
		
		//각 정점을 순회함
		//이때 이미 순회한 정점이거나 자기 자신으로 시작하지 않는 정점이면 탐색하지 않는다.
		//순회 1번 = 연결 요소 1개
		
		for(int i=1;i<=vertices;i++) {
			if(visited[i] || graph.get(i).isEmpty())	continue;
			//System.out.println("start "+i+" >> ");
			
			search(i);
			count++;
		}
		//독립적인 정점(방문하지 않는 노드)도 연결요소로 취급한다.
		for(int i=1;i<visited.length;i++) {
			if(!visited[i])	count++;
		}
		
		
		bw.write(String.valueOf(count));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
	//연결 요소만큼 순회하는 매소드
	public static void search(int start) {
		visited[start]=true;
		//System.out.println(" >> "+graph.get(start).toString());
		for(int next : graph.get(start)) {
			if(visited[next] || graph.get(next).isEmpty())	continue;
				
			search(next);
		}
	}
}

