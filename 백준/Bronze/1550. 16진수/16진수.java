import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String hex = br.readLine();
		int num = Integer.parseInt(hex, 16);
		
		bw.write(String.valueOf(num));
		bw.newLine();
		br.close();
		bw.close();
	}

}