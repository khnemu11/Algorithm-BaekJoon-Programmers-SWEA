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
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		boolean [] visited = new boolean[n+1];
		int [] arr = new int [n+1];
		arr[0]=0;
		dfs(visited,arr,m,n,1);
		bw.newLine();
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void dfs(boolean [] visited, int [] arr, int m, int n, int depth) throws IOException {
		if(m<depth) {
			for(int i=1;i<=m;i++) {
				bw.write(String.valueOf(arr[i])+" ");
			}
			bw.newLine();
		}
		else {
			for(int i=1;i<=n;i++) {
				if(arr[depth-1]<i && visited[i]==false) {
					arr[depth]=i;
					visited[i]=true;
					dfs(visited,arr,m,n,depth+1);
					visited[i]=false;
				}
			}
		}	
	}
}