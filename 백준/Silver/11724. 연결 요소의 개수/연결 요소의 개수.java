import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	노드와 노드를 연결하여 루트의 개수를 구하는 문제 -> 유니온 파인드 이용하여 부모 노드 저장
*/
public class Main {
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 노드의 개수
		int M = Integer.valueOf(st.nextToken()); // 간선의 개수
		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i] = i; // 부모를 자기 자신으로 초기화
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());

			if (getParent(from) != getParent(to)) { // 노드1과 노드2의 부모가 다르면 병합
				union(from, to);
			}

		}

		HashSet<Integer> set = new HashSet<>(); // 중복되지 않는 부모의 set

		for (int i = 1; i < parents.length; i++) {
			getParent(i); // 부모 최신화
			set.add(parents[i]);
		}
		bw.write(set.size() + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static int getParent(int child) { // 부모를 구하는 메소드 (자식)
		if (child == parents[child]) {
			return child;
		}
		parents[child] = getParent(parents[child]);

		return parents[child];
	}

	public static void union(int a, int b) { // 부모를 병합하는 메소드 (자식1, 자식2)
		int pa = getParent(a);
		int pb = getParent(b);

		if (pa < pb) { // 자식 노드의 값이 작은 것을 우선순위로 부모로 설정
			parents[pb] = pa;
		} else {
			parents[pa] = pb;
		}
	}
}