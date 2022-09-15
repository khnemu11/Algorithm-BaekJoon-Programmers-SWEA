import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input;
		while((input = br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(input);
			
			int n = Integer.valueOf(st.nextToken())+1;
			int shares = Integer.valueOf(st.nextToken());
			
			bw.write(String.valueOf(shares/n));
			bw.newLine();
			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}