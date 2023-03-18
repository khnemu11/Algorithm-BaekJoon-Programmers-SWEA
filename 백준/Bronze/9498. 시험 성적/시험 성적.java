import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int num = Integer.valueOf(br.readLine());

		if (num > 89) {
			bw.write("A");
		}
		else if (num > 79) {
			bw.write("B");
		}
		else if (num > 69) {
			bw.write("C");
		}
		else if (num > 59) {
			bw.write("D");
		}else {
			bw.write("F");
		}

		bw.newLine();
		bw.flush();
		bw.close();
	}
}