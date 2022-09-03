import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int [] nums = new int[N];
		
		for(int i=0;i<N;i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		
		int max = nums[0];
		int sum = nums[0];
		for(int i=1;i<N;i++) {
			sum = Math.max(nums[i], sum+nums[i]);
			max = Math.max(max, sum);
			
		}
		bw.write(String.valueOf(max));
		
		bw.flush();
		bw.close();
		br.close();

	}
}
