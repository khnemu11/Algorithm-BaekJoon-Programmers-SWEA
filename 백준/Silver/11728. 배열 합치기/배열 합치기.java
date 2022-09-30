import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		ArrayList<Integer> list = new ArrayList<Integer>();

		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int num = Integer.valueOf(st.nextToken());
			list.add(num);
		}

		st = new StringTokenizer(br.readLine());

		while (st.hasMoreTokens()) {
			int num = Integer.valueOf(st.nextToken());
			list.add(num);
		}

		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append(" ");
		}

		bw.write(sb.deleteCharAt(sb.length() - 1).toString());
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
