import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static char map[][];
	static Map<String, Integer> cntByStr = new HashMap<>();
	static int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };	//8방향 벡터
	static int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int MAX_LENGTH = 5;	//문자열의 최대 길이

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 확인해야 하는 문자열 수

		map = new char[height][width];
		//맵 초기화
		for (int i = 0; i < height; i++) {
			String input = br.readLine();
			for (int j = 0; j < width; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		//모든 좌표로 시작하여 문자열을 생성
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				StringBuilder sb = new StringBuilder();
				sb.append(map[i][j]);
				makeStr(sb, new Coordinate(i, j));
			}
		}
		
		//찾으려는 문자열의 개수를 출력
		for (int i = 0; i < K; i++) {
			String target = br.readLine();

			int count = cntByStr.getOrDefault(target, 0);

			bw.write(count + "\n");
		}

		bw.flush();
	}
	//백트래킹을 이용해 1 ~ 5길이의 모든 8방향으로 이동했을 때의 문자열을 만들어주는 메소드
	public static void makeStr(StringBuilder sb, Coordinate curr) {
		//현재 문자열의 개수 증가ㅣ
		String key = sb.toString();
		cntByStr.put(key, cntByStr.getOrDefault(key, 0) + 1);
		
		//길이가 최대값 보다 크거나 같으면 더이상 생성 안함
		if (sb.length() >= MAX_LENGTH) {
			return;
		}
		//8방향으로 이동하여 다음 문자열 생성
		for (int i = 0; i < dx.length; i++) {
			Coordinate next = new Coordinate(curr.row + dx[i], curr.col + dy[i]);
			
			//맵을 벗어났을 때의 규칙 적용
			next.row = next.row < 0 ? map.length - 1 : next.row;
			next.row = next.row >= map.length ? 0 : next.row;
			next.col = next.col < 0 ? map[0].length - 1 : next.col;
			next.col = next.col >= map[0].length ? 0 : next.col;

			sb.append(map[next.row][next.col]);
			makeStr(sb, next);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
