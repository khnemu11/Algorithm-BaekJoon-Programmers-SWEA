import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int a[] = new int[N + 1];
		int b[] = new int[N + 1];
		b[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			a[i] = b[i - 1];
			b[i] = a[i - 1] + b[i - 1];
		}

		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(a[N]));
		sb.append(String.valueOf(" "));
		sb.append(String.valueOf(b[N]));

		bw.write(sb.toString());
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}

}
