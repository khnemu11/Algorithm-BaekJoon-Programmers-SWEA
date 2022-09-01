import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			
			if(n==0 && m==0) {
				break;
			}
			if(m%n==0) {
				bw.write("factor");
			}
			else if(n%m==0) {
				bw.write("multiple");
			}
			else {
				bw.write("neither");
			}
			bw.newLine();
		}	
		bw.flush();
		bw.close();
		br.close();
	}
}
