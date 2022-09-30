import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.valueOf(br.readLine());
		if(num%2==0) {
			bw.write("SK");
		}
		else {
			bw.write("CY");
		}
		
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
