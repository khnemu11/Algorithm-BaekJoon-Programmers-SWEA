import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());
		
		double circle1 = Math.PI*n*n;
		double circle2 = n*n*2;
		bw.write(String.format("%.6f",circle1));
		bw.newLine();
		bw.write(String.format("%.6f",circle2));
		bw.newLine();
		
		bw.flush();
		br.close();
		bw.close();
	}
}