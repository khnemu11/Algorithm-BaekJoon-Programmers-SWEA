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
		Map<String,Integer> map = new HashMap<>();
		int count = Integer.valueOf(br.readLine());
		int max = 0;
		String maxKey = "";
		for(int i=0;i<count;i++) {
			String key = br.readLine();
			map.put(key,map.getOrDefault(key, 0)+1);
			int value = map.get(key);
			
			if(value > max) {
				max = value;
				maxKey = key;
			}
			else if(value == max) {
				maxKey = maxKey.compareTo(key) > 0 ? key: maxKey;
			}
		}
		bw.write(maxKey);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
