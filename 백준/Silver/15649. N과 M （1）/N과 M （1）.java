import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//이전 상황으로 돌아가려는 알고리즘 필요시 -> 백트래킹 -> dfs or bfs
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		boolean [] visited = new boolean[n+1];
		int [] arr = new int[n+1];
		dfs(visited,1,n,m,arr);
		
		bw.flush();
		br.close();
		bw.close();
		
	}
	
	public static void dfs(boolean[] visited,int depth,int n,int m,int [] arr) throws IOException {
		if(depth>m) {
			
			for(int i=1;i<=m;i++) {
				bw.write(String.valueOf(arr[i]));
				bw.write(" ");
			}
			bw.newLine();
		}else {
			for(int i=1;i<=n;i++) {
				if(visited[i])	continue;
				visited[i]=true;
				arr[depth]=i;
				dfs(visited,depth+1,n,m,arr);
				visited[i]=false;
			}
		}
	}

}