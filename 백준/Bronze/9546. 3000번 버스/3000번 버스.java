import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int N = Integer.valueOf(br.readLine());
		
		for(int testcase =0;testcase<N;testcase++) {
			int num = Integer.valueOf(br.readLine());
			
			bw.write(String.valueOf((int)(Math.pow(2, num)-1)));
			bw.newLine();
		}
		                                 
		
		bw.flush();
		bw.close();
		br.close();
	}
}
