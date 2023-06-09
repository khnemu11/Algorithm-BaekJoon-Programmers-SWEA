import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int [] amount;
	static int [] dp;
	static boolean [] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		amount = new int [N];
		dp = new int [N];
		visited = new boolean [N];
		
		
		for(int i=0;i<N;i++) {
			amount[i]=Integer.valueOf(br.readLine());
		}
		
		bw.write(String.valueOf(drink(N-1)));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static int drink(int depth) {
		if(depth<0) {
			return 0;
		}
		else if(visited[depth]) {
			return dp[depth];
		}
		else if(depth==0) {
			visited[depth]=true;
			dp[depth] = amount[0];
			return dp[depth];
		}
		else if(depth==1) {
			visited[depth]=true;
			dp[depth] = amount[0]+amount[1];
			return dp[depth];
		}
		else{
			dp[depth]=Math.max(Math.max(drink(depth-3)+amount[depth-1], drink(depth-2))+amount[depth], drink(depth-1));
			visited[depth]=true;
		}
		
		return dp[depth];
	}
}