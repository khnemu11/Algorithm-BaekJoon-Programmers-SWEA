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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int ax = Integer.valueOf(st.nextToken());
		int ay = Integer.valueOf(st.nextToken());
		int az = Integer.valueOf(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int cx = Integer.valueOf(st.nextToken());
		int cy = Integer.valueOf(st.nextToken());
		int cz = Integer.valueOf(st.nextToken());

		sb.append(cx - az);
		sb.append(" ");
		sb.append(cy / ay);
		sb.append(" ");
		sb.append(cz - ax);

		bw.write(sb.toString());
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
