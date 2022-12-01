import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String fan = ":fan:";
		String main = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		sb.append(fan).append(fan).append(fan).append("\n");
		sb.append(fan).append(":").append(main).append(":").append(fan).append("\n");
		sb.append(fan).append(fan).append(fan).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
	}

}
