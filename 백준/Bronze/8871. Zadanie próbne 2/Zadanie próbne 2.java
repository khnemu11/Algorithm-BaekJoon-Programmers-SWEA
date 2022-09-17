import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int count = Integer.valueOf(br.readLine()) + 1;

		StringBuilder sb = new StringBuilder();
		
		sb.append(count*2);
		sb.append(" ");
		sb.append(count*3);
		
		bw.write(sb.toString());
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
