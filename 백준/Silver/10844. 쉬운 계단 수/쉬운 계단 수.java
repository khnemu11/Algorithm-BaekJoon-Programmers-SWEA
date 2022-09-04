import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static long [][] dp;
	static boolean [][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		long result=0;
		dp = new long[N+1][10];
		visited = new boolean [N+1][10];
		for (int i = 1; i <10; i++) {
			result+=find(N-1,i);
		}
		bw.write(String.valueOf(result%1000000000));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static long find(int depth,int curr) {
		if(depth==0) {
			return 1;
		}
		else if(visited[depth][curr]) {
			return dp[depth][curr]%1000000000;
		}
		else if(depth>0){
			if(curr==0) {
				dp[depth][curr]=find(depth-1,curr+1)%1000000000;
			}
			else if(curr==9) {
				dp[depth][curr]=find(depth-1,curr-1)%1000000000;
			}
			else {
				dp[depth][curr]=(find(depth-1,curr-1) + find(depth-1,curr+1))%1000000000;
			}
			visited[depth][curr]=true;
		}
		return dp[depth][curr]%1000000000;
	}
}
