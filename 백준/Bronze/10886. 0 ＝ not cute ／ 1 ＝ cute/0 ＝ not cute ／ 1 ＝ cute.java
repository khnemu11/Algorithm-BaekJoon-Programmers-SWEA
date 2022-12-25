import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int num = Integer.valueOf(br.readLine());
		int cute = 0;

		for (int i = 0; i < num; i++) {
			if (Integer.valueOf(br.readLine()) == 1) {
				cute++;
			}
		}
		if (cute > num / 2) {
			bw.write("Junhee is cute!");
		} else {
			bw.write("Junhee is not cute!");
		}
		bw.newLine();
		bw.flush();
	}

}
