import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine().replaceAll("[1]+", "1");
		input = input.replaceAll("[0]+", "0");
		
		int length1 = input.length()-input.replace("0", "").length();
		int count = length1 > input.length()/2 ? input.length() - length1: length1;
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
}