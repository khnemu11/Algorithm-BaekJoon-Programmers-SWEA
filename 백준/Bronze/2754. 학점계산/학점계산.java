import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Map<String,String> scores = new HashMap<>();
		
		scores.put("A+", "4.3");
		scores.put("A0", "4.0");	
		scores.put("A-", "3.7");
		scores.put("B+", "3.3");
		scores.put("B0", "3.0");
		scores.put("B-", "2.7");
		scores.put("C+", "2.3");
		scores.put("C0", "2.0");
		scores.put("C-", "1.7");
		scores.put("D+", "1.3");
		scores.put("D0", "1.0");
		scores.put("D-", "0.7");
		scores.put("F", "0.0");
		
		bw.write(scores.get(br.readLine()));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}