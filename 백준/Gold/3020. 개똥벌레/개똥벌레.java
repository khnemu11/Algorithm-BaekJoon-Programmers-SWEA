import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static List<Map<Integer, Integer>> bottomWallHitCntMap = new ArrayList<>();
	static int BOTTOM = 0;
	static int TOP = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int H = Integer.valueOf(st.nextToken());

		List<Integer> bottomWalls = new ArrayList<>();
		List<Integer> topWalls = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int val = Integer.valueOf(br.readLine());
			if (i % 2 == 1) {
				topWalls.add(val);
			} else {
				bottomWalls.add(val);
			}
		}
//		System.out.println(bottomWalls);
		setMap(bottomWalls, H);
//		System.out.println(topWalls);
		setMap(topWalls, H);

//		System.out.println(bottomWallHitCntMap);

		int minHit = Integer.MAX_VALUE;
		int cnt = 0;

		for (int height = 1; height <= H; height++) {
			int hit = bottomWallHitCntMap.get(BOTTOM).getOrDefault(height, 0)
					+ bottomWallHitCntMap.get(TOP).getOrDefault(H - height + 1, 0);
//			System.out.println("height : " + height + " , hit count : " + hit);
			if (minHit > hit) {
				minHit = hit;
				cnt = 1;
			} else if (minHit == hit) {
				cnt++;
			}
		}

		bw.write(minHit + " " + cnt + "\n");
		bw.flush();
	}

	public static void setMap(List<Integer> walls, int height) {
		Map<Integer, Integer> hitMap = new HashMap<>();

		Collections.sort(walls, Collections.reverseOrder());
		int beforeWall = walls.get(0);

		for (int i = 0; i < walls.size(); i++) {
			if (beforeWall != walls.get(i)) {
				for (int j = walls.get(i); j < beforeWall; j++) {
					hitMap.put(j, hitMap.get(beforeWall));
				}

				beforeWall = walls.get(i);
			}
			hitMap.put(walls.get(i), hitMap.getOrDefault(walls.get(i), 0) + 1);
		}

		for (int i = 1; i < beforeWall; i++) {
			hitMap.put(i, hitMap.get(beforeWall));
		}

		bottomWallHitCntMap.add(hitMap);
	}
}