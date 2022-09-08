import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());

		long paths[] = new long[n - 1];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n - 1; i++) {
			paths[i] = Integer.valueOf(st.nextToken());
		}
		
		long min = Integer.MAX_VALUE;
		long sum = 0;
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n-1; i++) {
			long cost = Integer.valueOf(st.nextToken());
			
			if(min>cost) {
				min = cost;
			}
			sum+=min*paths[i];
		}

		bw.write(String.valueOf(sum));
		bw.newLine();
		
		bw.flush();
		br.close();
		bw.close();
	}
}