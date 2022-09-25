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
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			String name = st.nextToken();
			int age = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			
			if(name.equals("#") && age == 0 && weight == 0) {
				break;
			}
			sb.append(name);
			sb.append(" ");
			if(age>17 || weight>=80) {
				sb.append("Senior");
			}
			else {
				sb.append("Junior");
			}
			bw.write(sb.toString());
			bw.newLine();
		}
		
		
		bw.close();
		br.close();
	}
}