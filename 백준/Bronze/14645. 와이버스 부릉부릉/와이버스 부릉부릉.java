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
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int T = Integer.valueOf(st.nextToken());
		
		for(int i=0;i<T;i++) {
			br.readLine();
		}

		
		bw.write("비와이");
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
