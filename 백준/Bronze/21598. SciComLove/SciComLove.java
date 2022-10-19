import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int count = Integer.valueOf(br.readLine());
		
		for(int i=0;i<count;i++) {
			bw.write("SciComLove");
			bw.newLine();
		}

		bw.flush();
		br.close();
		bw.close();
	}
}