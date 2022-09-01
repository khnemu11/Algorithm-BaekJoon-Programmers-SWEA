import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int firstSize = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Map<String,Integer> cards = new HashMap<>();
		
		for(int i=0;i<firstSize;i++) {
			String num = st.nextToken();
			if(cards.containsKey(num)) {
				cards.put(num, cards.get(num)+1);
			}
			else {
				cards.put(num, 1);
			}
		}
		
		int secondSize = Integer.valueOf(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<secondSize;i++) {
			String findNum = st.nextToken();
			
			if(cards.containsKey(findNum)) {
				bw.write(String.valueOf(cards.get(findNum)));
			}
			else {
				bw.write('0');
			}
			
			bw.write(" ");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}