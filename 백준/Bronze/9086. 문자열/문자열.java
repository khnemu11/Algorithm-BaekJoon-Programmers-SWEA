import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.valueOf(br.readLine());

		for(int i=0;i<T;i++) {
			String word = br.readLine();
			sb.append(word.charAt(0));
			sb.append(word.charAt(word.length()-1));
			sb.append("\n");
		}
		
		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}
}
