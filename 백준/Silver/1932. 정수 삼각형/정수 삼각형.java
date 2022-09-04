import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] cost;
	static int[][] visited = new int[500][500];;
	static int max=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		cost = new int[N][];
		visited = new int[N][];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i] = new int [i+1];
			visited[i] = new int [i+1];
			
			for(int j=0;j<=i;j++) {
				cost[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		sumCost(0);
		bw.write(String.valueOf(max));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static void sumCost(int depth) {
		if(depth==cost.length) {
			for(int i=0;i<depth;i++) {
				if(visited[depth-1][i]>max) {
					max = visited[depth-1][i];
				}
			}
			
			return ;
		}
		else if(depth==0) {
			visited[depth][depth] = cost[0][0];
			
			sumCost(depth+1);
		}
		else{
			for(int i=0;i<=depth;i++) {
				if(i==0) {
					visited[depth][i] =visited[depth-1][i] + cost[depth][i];
				}
				else if(i==depth) {
					visited[depth][i] =visited[depth-1][i-1] + cost[depth][i];
				}
				else {
					visited[depth][i] = Math.max(visited[depth-1][i-1], visited[depth-1][i])+cost[depth][i];
				}
			}
			sumCost(depth+1);
		}
	
	}
}
