import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.valueOf(st.nextToken());
		int s = Integer.valueOf(st.nextToken());
		int r2 = 2*s - r1;
		
		bw.write(String.valueOf(r2));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
