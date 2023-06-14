import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 
 * 1: 생성
 * 2: 파괴
 * 
 * 선행 건물을 지어야 다음 건물을 지을 수 있다
 * -> 위상 정렬
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int CREATE = 1;
		int DESTROY = 2;
		int N = Integer.parseInt(st.nextToken()); // 건물 개수
		int M = Integer.parseInt(st.nextToken()); // 관계 개수
		int K = Integer.parseInt(st.nextToken()); // 명령어 개수

		int parentNums[] = new int[N + 1]; // 건물 별 남은 선행 건물 개수
		int created[] = new int[N + 1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 건물 그래프

		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 그래프 등록
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			parentNums[child]++; // 위상 정렬 등록
			graph.get(parent).add(child);
		}
		boolean valid = true;
		// 건물 부수기/생성하기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int command = Integer.parseInt(st.nextToken());
			int buildNo = Integer.parseInt(st.nextToken());

			// 건물 생성
			if (command == CREATE) {
				// 선행 건물이 다 지어지지 않았는데 짓는 경우
				if (parentNums[buildNo] > 0) {
					valid = false;
					break;
				}
				//건물 생성
				created[buildNo]++;
				
				//선행 건물이 처음 지어지면 하위 건물을 지을 수 있다
				if (created[buildNo] == 1) {
					for (int child : graph.get(buildNo)) {
						parentNums[child]--;
					}
				}
			}
			// 건물 파괴
			else if (command == DESTROY) {
				// 건물이 지어지지도 않았는데 파괴할 경우
				if (created[buildNo] == 0) {
					valid = false;
					break;
				}
				//건물 개수 감소
				created[buildNo]--;
				//모든 건물 개수가 감소되면 
				if (created[buildNo] == 0) {
					for (int child : graph.get(buildNo)) {
						parentNums[child]++;
					}
				}
			}
		}

		// 최종 판단 후 출력
		if (valid) {
			bw.write("King-God-Emperor\n");
		} else {
			bw.write("Lier!\n");
		}
		bw.close();
	}
}
