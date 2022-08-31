import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());
		int [] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.valueOf(br.readLine());
		}
		Arrays.sort(nums);
		for(int i=0;i<n;i++) {
			bw.write(String.valueOf(nums[i]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}