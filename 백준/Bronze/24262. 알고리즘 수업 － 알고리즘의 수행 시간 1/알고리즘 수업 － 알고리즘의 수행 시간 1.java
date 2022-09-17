import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		br.readLine();

		bw.write("1");
		bw.newLine();
		bw.write("0");
		bw.newLine();
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
