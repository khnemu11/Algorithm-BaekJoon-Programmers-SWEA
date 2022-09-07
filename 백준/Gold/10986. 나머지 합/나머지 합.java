import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 합을 나누는 수
		long sum = 0;
		long count[] = new long[m];
		long result = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n+1; i++) {
			sum += Integer.parseInt(st.nextToken());
			sum %= m;
			count[(int)sum]++;
		}
		
		result += count[0];
		for(int i = 0; i < m; i++) {
			result += (count[i]*(count[i]-1))/2;
		}
		
		System.out.println(result);
	}

}