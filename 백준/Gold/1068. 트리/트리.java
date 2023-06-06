import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 트리 삭제 -> 삭제 배열에 기록
 * 삭제된 노드는 탐색을 안하도록
 * 
 * */
public class Main {
	static List<Integer> graph[];
	static int count = 0;
	static boolean deleted[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		graph = new List[N];
		deleted = new boolean[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		int parents[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		List<Integer> rootList = new ArrayList<>();

		for (int i = 0; i < parents.length; i++) {
			if (parents[i] == -1) {
				rootList.add(i);
			} else {
				graph[parents[i]].add(i);
			}
		}

		int deleteNode = Integer.parseInt(br.readLine());

		deleteNode(deleteNode);
		for (int root : rootList) {
			search(root);
		}

		bw.write(count + "\n");
		bw.close();
	}

	public static void deleteNode(int curr) {
		deleted[curr] = true;
		for (int child : graph[curr]) {
			deleteNode(child);
		}
	}

	public static void search(int curr) {
		if (deleted[curr]) {
			return;
		}

		int childCnt = 0;

		for (int child : graph[curr]) {
			if (deleted[child]) {
				continue;
			}
			search(child);
			childCnt++;
		}
		if (childCnt == 0) {
			count++;
		}
	}
}
