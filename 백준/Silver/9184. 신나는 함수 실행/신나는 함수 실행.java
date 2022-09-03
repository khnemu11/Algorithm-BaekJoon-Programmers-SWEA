import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int [][][]dp = new int [102][102][102];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());

			if (a == -1 && b == -1 && c == -1) {
				break;
			}

			int result = w(a, b, c);
			sb.append("w(");
			sb.append(a);
			sb.append(", ");
			sb.append(b);
			sb.append(", ");
			sb.append(c);
			sb.append(") = ");
			sb.append(result);
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	public static int w(int a, int b, int c) {		
		if(dp[a+50][b+50][c+50]>0) {
			return dp[a+50][b+50][c+50];
		}
		else {
			if(a<=0 || b<=0|| c<=0) {
				dp[a+50][b+50][c+50]=1;
				return 1;
			}
			else if(a > 20|| b > 20 || c > 20) {
				dp[a+50][b+50][c+50]=w(20,20,20);
				
				return dp[a+50][b+50][c+50];
			}
			if (a < b && b < c) {
				dp[a+50][b+50][c+50] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
				
				return dp[a+50][b+50][c+50];
			} 

			else {
				dp[a+50][b+50][c+50] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
				
				return dp[a+50][b+50][c+50];
			}
		}
	}
}
