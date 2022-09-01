import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int [] arr = new int[n]; 
		
		for(int i=0;i<n;i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		
		Arrays.sort(arr);
		bw.write(String.valueOf(arr[0]*arr[arr.length-1]));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
