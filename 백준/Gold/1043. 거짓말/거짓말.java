import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	진실을 아는 사람이 포함된 파티에 참가하면 안됨
	진실을 아는 사람이 있는 경우 해당 사람도 진실을 아는 사람으로 변경 -> bfs로 유니온-파인드 적용
	해당 파티도 참가 불가
*/
public class Main {
	static int parents[];
	static boolean truth[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 사람 수
		int M = Integer.valueOf(st.nextToken()); // 파티 수
		parents = new int[N + 1];
		ArrayList<ArrayList<Integer>> party = new ArrayList<>();

		for (int i = 1; i < parents.length; i++) { // 부모 노드 초기화
			parents[i] = i;
		}
		for (int i = 0; i <= M; i++) { // 파티 리스트 초기화
			party.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());

		int TruthNum = Integer.valueOf(st.nextToken()); // 진실을 아는 사람의 수
		truth = new boolean[N + 1];

		for (int i = 0; i < TruthNum; i++) {
			int peopleIdx = Integer.valueOf(st.nextToken());
			truth[peopleIdx] = true; // 진실을 아는사람 추기화
		}

		for (int partyIdx = 1; partyIdx <= M; partyIdx++) {
			st = new StringTokenizer(br.readLine());
			int partyNum = Integer.valueOf(st.nextToken());

			for (int i = 0; i < partyNum; i++) { // 해당 파티에 사람 인덱스 추가
				party.get(partyIdx).add(Integer.valueOf(st.nextToken()));
			}

			for (int i = 0; i < partyNum - 1; i++) {
				union(party.get(partyIdx).get(i), party.get(partyIdx).get(i + 1)); // 모든 파티원 병합
			}
		}

		int partyNum = 0;

		for (int partyIdx = 1; partyIdx < party.size(); partyIdx++) {
			boolean canEnterParty = true;
			for (int peopleIdx : party.get(partyIdx)) {
				if (truth[getParent(peopleIdx)]) {	//파티원의 부모가 진실을 아는 사람이면 참가 못함
					canEnterParty = false;
					break;
				}
			}

			if (canEnterParty) {
				partyNum++;
			}
		}

		System.out.println(partyNum);

	}

	public static int getParent(int child) {
		if (parents[child] == child) {
			return child;
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
		if (truth[pa] || truth[pb]) { // 진실을 아는 부모 노드면 둘다 참으로 변경
			truth[pa] = true;
			truth[pb] = true;
		}
	}
}