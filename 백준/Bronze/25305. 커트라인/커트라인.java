import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int grade = Integer.valueOf(st.nextToken());
		
		Integer [] scores = new Integer[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			scores[i] = Integer.valueOf(st.nextToken());
		}
		
		Arrays.sort(scores,Collections.reverseOrder());
		bw.write(String.valueOf(scores[grade-1]));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}