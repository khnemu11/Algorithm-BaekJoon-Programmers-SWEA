import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int [] times = new int[N]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			times[i] = Integer.valueOf(st.nextToken());
		}	
		int min=0;
		
		Arrays.sort(times);
		
		for(int i=0;i<N;i++) {
			min = min + times[i]*(N-i);
		}
		
		bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
		br.close();
	}
}
