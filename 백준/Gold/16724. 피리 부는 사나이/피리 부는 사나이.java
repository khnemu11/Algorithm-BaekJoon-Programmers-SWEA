import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int parents[];	//분리 집합을 위한 부모 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		parents = new int[width * height];
		
		//부모 배열 초기화
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		
		//그래프 탐색
		for (int row = 0; row < height; row++) {
			String input = br.readLine();

			for (int col = 0; col < input.length(); col++) {
				char direction = input.charAt(col); // 현재 위치
				int parent = row * width + col;	//2차원 맵을 1차원으로 했을 때 나오는 현재 인덱스
				int child = 0;	//다음 방향의 인덱스
				
				//상하좌우 방향에 따른 다음 방향 인덱스 설정
				if (direction == 'D') {
					child = (row + 1) * width + col;
				} else if (direction == 'L') {
					child = row * width + col - 1;
				} else if (direction == 'R') {
					child = row * width + col + 1;
				} else if (direction == 'U') {
					child = (row - 1) * width + col;
				}
				
				//부모 자식 연결
				union(child, parent);
			}
		}

		Set<Integer> safeZone = new HashSet<>();	//safe zone 의 개수 = 루트 부모의 개수

		for (int i = 0; i < parents.length; i++) {
			safeZone.add(getParent(parents[i]));
		}

		bw.write(safeZone.size() + "\n");
		bw.flush();
	}

	public static int getParent(int child) {
		if (parents[child] == child) {
			return parents[child];
		}

		parents[child] = getParent(parents[child]);
		return parents[child];
	}

	public static void union(int child, int parent) {
		int pc = getParent(child);
		int pp = getParent(parent);

		parents[pc] = pp;
	}
}
