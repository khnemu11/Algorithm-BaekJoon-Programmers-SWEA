import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int no = 0;
		while (true) {
			no++;
			int num = Integer.valueOf(br.readLine());
			if (num == 0) {
				break;
			}
			HashMap<Integer, String> children = new HashMap<>();
			Queue<Integer> nastyFrom = new LinkedList<>();
			Queue<Integer> nastyTo = new LinkedList<>();
			for (int i = 1; i <= num; i++) {
				int next = i;
				StringTokenizer st = new StringTokenizer(br.readLine());
				children.put(i, st.nextToken());
				for (int j = 1; j < num; j++) {
					next--;
					if (next == 0) {
						next = num;
					}
					if (st.nextToken().equals("P")) {
						continue;
					} else {
						nastyFrom.add(next);
						nastyTo.add(i);
					}
				}
			}

			sb.append("Group ");
			sb.append(no);
			sb.append("\n");

			if (nastyFrom.isEmpty()) {
				sb.append("Nobody was nasty");
				sb.append("\n");
			} else {
				while (!nastyFrom.isEmpty()) {
					int from = nastyFrom.poll();
					int to = nastyTo.poll();

					sb.append(children.get(from));
					sb.append(" was nasty about ");
					sb.append(children.get(to));
					sb.append("\n");
				}
			}
//			System.out.println(nastyFrom.toString());
//			System.out.println(nastyTo.toString());
			sb.append("\n");
		}
		bw.write(sb.deleteCharAt(sb.length()-1).toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
