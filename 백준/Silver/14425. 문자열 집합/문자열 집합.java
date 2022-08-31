import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<String,Integer> words = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int count =0;
		
		for (int i = 0; i < n; i++) {
			words.put(br.readLine(), 1);
		}
		
		for (int i = 0; i < m; i++) {
			if(words.get(br.readLine())!=null) {
				count++;
			}
		}
		
		bw.write(String.valueOf(count));
		bw.newLine();	
		
		bw.flush();
		bw.close();
		br.close();
	}
}
