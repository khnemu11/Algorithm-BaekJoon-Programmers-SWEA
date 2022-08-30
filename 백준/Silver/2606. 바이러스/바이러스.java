import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int count=0;
	static boolean [][]network;
	static boolean []visited;
	
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int vertices = Integer.valueOf(br.readLine());
		int edges =  Integer.valueOf(br.readLine());
		network = new boolean[vertices+1][vertices+1];
		visited = new boolean[vertices+1];
		
		for(int i=0;i<edges;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
		
			if(network[from][to]==false){
				network[from][to]=true;
				network[to][from]=true;
			}
		}
		dfs(1,vertices);
		
		bw.write(String.valueOf(count));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs (int from,int vertices) {
		for(int i=2;i<=vertices;i++) {
		//	System.out.println("from : "+from +" to : "+i+ ": "+network[from][i]);
			if(!network[from][i] || visited[i])	continue;
			else if(network[from][i] && !visited[i]) {
				count++;
				visited[i]=true;
				dfs(i,vertices);
			}
		}
		
	}
}
