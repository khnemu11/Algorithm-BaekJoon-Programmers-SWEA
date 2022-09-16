import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int t = 0; t < T; t++) {
			int num = Integer.valueOf(br.readLine());
			TreeMap<Integer,Integer> map = new TreeMap<>();
			for (int i = 0; i < num; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				String command = st.nextToken();
				int val = Integer.valueOf(st.nextToken());

				if (command.equals("I")) {
					map.put(val,map.getOrDefault(val, 0)+1);
				} else if(map.size()>0){
					if(val == 1) {
						if(map.get(map.lastKey())>1) {
							map.put(map.lastKey(),map.get(map.lastKey())-1);
						}
						else {
							map.remove(map.lastKey());
						}
					}
					else {
						if(map.get(map.firstKey())>1) {
							map.put(map.firstKey(),map.get(map.firstKey())-1);
						}
						else {
							map.remove(map.firstKey());
						}
					}
				}
			}

			if (map.size() < 1) {
				bw.write("EMPTY");
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(map.lastKey());
				sb.append(" ");
				sb.append(map.firstKey());

				bw.write(sb.toString());
			}
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

}