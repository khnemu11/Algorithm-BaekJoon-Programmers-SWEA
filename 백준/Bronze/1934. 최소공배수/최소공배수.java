import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.valueOf(br.readLine());
		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			int gcd = a*b/LCS(Math.max(a, b),Math.min(a, b));
			
			bw.write(String.valueOf(gcd));
			bw.newLine();
		}
		
		bw.close();
		br.close();
	}

	static int LCS(int a, int b) {
		int lcs = 0;

		if (a % b != 0) {
			lcs = LCS(b, a % b);
		} else {
			lcs = b;
		}
		return lcs;
	}
}
