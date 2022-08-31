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
		Map<Integer,Integer> card = new HashMap<>();
		int n = Integer.valueOf(br.readLine());
		
		Integer [] nums = new Integer[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			card.put(Integer.valueOf(st.nextToken()),1);
		}
		
		n = Integer.valueOf(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			if(card.get(Integer.valueOf(st.nextToken())) != null ){
				bw.write('1');
			}
			else {
				bw.write('0');
			}	
			
			bw.write(' ');
		}
		
		
		bw.newLine();	
		
		bw.flush();
		bw.close();
		br.close();
	}
}
