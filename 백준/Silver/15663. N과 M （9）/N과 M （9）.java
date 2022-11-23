import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];
	static Set<String> ouput;
	static int num[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int length = Integer.valueOf(st.nextToken());

		num = new int[size + 1];
		visited = new boolean[size + 1];
		ouput = new LinkedHashSet<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= size; i++) {
			num[i] = Integer.valueOf(st.nextToken());
		}

		Arrays.sort(num);
		ArrayList<Integer> list = new ArrayList<>();
		dfs(0, length, list);

		Iterator<String> it = ouput.iterator();

		while (it.hasNext()) {
			bw.write(it.next());
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}

	public static void dfs(int curr, int depth, ArrayList<Integer> list) {
		if (curr == depth) {
			StringBuilder sb = new StringBuilder();
//			System.out.println(list.toString());

			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i)).append(" ");

			}
			ouput.add(sb.deleteCharAt(sb.length() - 1).toString());
		} else {
			for (int i = 1; i < num.length; i++) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;
				list.add(num[i]);
				dfs(curr + 1, depth, list);
				visited[i] = false;
				list.remove(list.size() - 1);
			}
		}
	}
}
