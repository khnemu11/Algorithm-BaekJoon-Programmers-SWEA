import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.valueOf(br.readLine());
		
		Integer[]rope = new Integer[N];
		
		for(int i=0;i<N;i++) {
			rope[i]=Integer.valueOf(br.readLine());
		}
		
		Arrays.sort(rope,Collections.reverseOrder());
		
		int max = 0;
		
		for(int i=0;i<N;i++) {
			int weight = rope[i]*(i+1);
			
			if(max<weight) {
				max=weight;
			}
		}
		
		bw.write(String.valueOf(max));
		bw.newLine();
		
		bw.close();
		br.close();
	}
}