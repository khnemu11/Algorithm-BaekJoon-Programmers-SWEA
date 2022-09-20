import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashMap<String,String> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int mapCount = Integer.valueOf(st.nextToken());
		int findCount = Integer.valueOf(st.nextToken());
		
		for(int i=0;i<mapCount;i++) {
			st = new StringTokenizer(br.readLine());
			
			map.put(st.nextToken(), st.nextToken());
		}
		
		for(int i=0;i<findCount;i++) {
			bw.write(map.get(br.readLine()));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
