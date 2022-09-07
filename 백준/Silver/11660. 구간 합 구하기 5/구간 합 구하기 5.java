import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long result = 0;
		long sum[][] = new long[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				sum[i][j] = sum[i][j-1] + Integer.valueOf(st.nextToken());
			}
		}
		
		for (int t = 0; t < M; t++) {
			result=0;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.valueOf(st.nextToken());
			int y1 = Integer.valueOf(st.nextToken());
			int x2 = Integer.valueOf(st.nextToken());
			int y2 = Integer.valueOf(st.nextToken());
			
			for(int i=x1;i<=x2;i++) {
				result += sum[i][y2] - sum[i][y1-1];
			}
			bw.write(String.valueOf(result));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}