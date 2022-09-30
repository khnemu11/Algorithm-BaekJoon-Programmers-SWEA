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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int size = Integer.valueOf(st.nextToken());
		int seq = Integer.valueOf(st.nextToken())-1;
		int arr[] = new int [size];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<size;i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		
		Arrays.sort(arr);
		bw.write(String.valueOf(arr[seq]));
		bw.close();
		br.close();
	}
}
