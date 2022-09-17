import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int result=0;
		
		for(int i=0;i<5;i++) {
			result+=Integer.valueOf(br.readLine());
		}
		
		bw.write(String.valueOf(result));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
