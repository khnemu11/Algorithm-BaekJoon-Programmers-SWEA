import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int []dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//딱봐도 무슨 규칙인지 모르겠으면 5번 정도 직접 나열해보고 규칙을 찾자 -> 피보나치
		int n = Integer.valueOf(br.readLine());
			
		dp=new int[n+2];
		dp[0]=0;
		dp[1]=1;
		dp[2]=2;
		
		bw.write(String.valueOf(countBinary(n)));;

		bw.flush();
		bw.close();
		br.close();

	}

	public static int countBinary(int n) {		
		if(n>2) {
			for(int i=3;i<=n;i++) {
				dp[i]=(dp[i-1]+dp[i-2])%15746;
			}
		}
		
		return dp[n];
	}
}
