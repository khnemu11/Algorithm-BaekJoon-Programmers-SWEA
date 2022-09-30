import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashMap<String,String> attendance = new HashMap<>();
		
		int num = Integer.valueOf(br.readLine());
		
		for(int i=0;i<num;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			String value = st.nextToken();
			
			attendance.put(key, value);
		}
		List<String>keys = new ArrayList<>(attendance.keySet());
		Collections.sort(keys,Collections.reverseOrder());
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<keys.size();i++){
			String key = keys.get(i);
			String value = attendance.get(key);
			if(value.equals("enter")) {
				sb.append(key);
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
