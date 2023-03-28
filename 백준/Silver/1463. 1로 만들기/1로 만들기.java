import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int [] dp;
	
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());
		dp = new int[n+1];
		dp[0]=0;
		dp[1]=0;
		bw.write(String.valueOf(twoThreeDivide(n)));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int twoThreeDivide(int num) {
		if(num==1 || num==0) {
			return 0;
		}
		else if(dp[num]==0) {
			if(num%6==0) {
				dp[num] = Math.min(twoThreeDivide(num/3), twoThreeDivide(num-1))+1;
				dp[num] = Math.min(twoThreeDivide(num/2)+1, dp[num]);
			}
			else if(num%3==0) {
				dp[num] = Math.min(twoThreeDivide(num/3), twoThreeDivide(num-1))+1;
			}
			else if(num%2==0) {
				dp[num] = Math.min(twoThreeDivide(num/2), twoThreeDivide(num-1))+1;
			}
			else {
				dp[num] = twoThreeDivide(num-1)+1;
			}
		}

		return dp[num];
	}
}