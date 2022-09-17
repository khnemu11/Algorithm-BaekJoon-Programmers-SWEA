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
		int score[] = {6,3,2,1,2};
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<2;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int result = 0;
			for(int j=0;j<5;j++) {
				result +=score[j]*Integer.valueOf(st.nextToken());
			}
			sb.append(result);
			sb.append(" ");
		}
		bw.write(sb.deleteCharAt(sb.length()-1).toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
