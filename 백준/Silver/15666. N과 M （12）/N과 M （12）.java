import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int length = Integer.valueOf(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < size; i++) {
			set.add(Integer.valueOf(st.nextToken()));
		}
		num = new ArrayList<>();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			num.add(it.next());
		}
		Collections.sort(num);
		dfs(0, length, 0, new ArrayList<Integer>());

		br.close();
	}

	public static void dfs(int curr, int depth, int start, ArrayList<Integer> list) {
		if (curr == depth) {
			StringBuilder sb = new StringBuilder();

			for (int candidate : list) {
				sb.append(candidate).append(" ");
			}

			System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
		}

		else {
			for (int i = start; i < num.size(); i++) {
				list.add(num.get(i));

				dfs(curr + 1, depth, i, list);

				list.remove(list.size() - 1);
			}
		}
	}
}
