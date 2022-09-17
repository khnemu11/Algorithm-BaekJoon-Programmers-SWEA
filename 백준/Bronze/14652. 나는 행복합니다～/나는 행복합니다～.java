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
		StringBuilder sb = new StringBuilder();
		
		int row = Integer.valueOf(st.nextToken());
		int col = Integer.valueOf(st.nextToken());
		int seat = Integer.valueOf(st.nextToken());
		
		int goalCol = seat%col;
		int goalRow = seat/col;
		
		sb.append(goalRow);
		sb.append(" ");
		sb.append(goalCol);
		
		bw.write(sb.toString());
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
