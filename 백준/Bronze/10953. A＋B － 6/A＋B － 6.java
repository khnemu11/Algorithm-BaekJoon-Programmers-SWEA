import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.valueOf(br.readLine());
		
		while(T-->0) {
			int sum = Arrays.stream(br.readLine().split(",")).mapToInt(x->Integer.valueOf(x)).sum();

			bw.write(String.valueOf(sum));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
