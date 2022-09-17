import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int T = Integer.valueOf(br.readLine());
		
		for(int i=0;i<T;i++) {
			String password = br.readLine();
			
			if(password.matches("^.{6,9}$")) {
				bw.write("yes");
			}
			else {
				bw.write("no");
			}
			bw.newLine();
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}
}
