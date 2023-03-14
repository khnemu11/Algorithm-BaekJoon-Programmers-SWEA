import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
	풀이 알고리즘
	
	1~4까지 가능한 비행기라면
	다음 1~4비행기는 1~3이 가능함
	이때 1~3의 비행기와 동일함 -> 유니온파인드를 이용해 병합
	최종적으로 부모가 0인 노드가 나올 때 까지 반복
 */
public class Main {
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		parents = new int[N + 1];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		int P = Integer.valueOf(br.readLine());

		int cnt = 0;
		for (int i = 0; i < P; i++) {
			int airplane = Integer.valueOf(br.readLine());

			if (getParent(airplane) == 0) {
				break;
			}
			union(getParent(airplane) - 1, airplane); // 현재 비행기를 이전 비행기의 이전칸에 넣을 수 있는지 확인
			cnt++;

		}
		bw.write(cnt + "\n");
		bw.flush();
	}

	public static int getParent(int child) {
		if (parents[child] == child) {
			return parents[child];
		}

		parents[child] = getParent(parents[child]);
		return parents[child];
	}

	public static void union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);

		if (pa < pb) {
			parents[pb] = pa;
		} else {
			parents[pa] = pb;
		}
	}
}