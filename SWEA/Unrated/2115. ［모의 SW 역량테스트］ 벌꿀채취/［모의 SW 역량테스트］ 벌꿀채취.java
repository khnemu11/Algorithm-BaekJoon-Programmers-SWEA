import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 *  0) 문제 목표
 *    벌꿀을 가로로 M개의 연속된 벌통을 겹치지 않게 2번 골라 C개가 넘지않는 합 N의 최대값
 * 
 *  1) 시간 복잡도
 *     제한 시간 : 3/50 = 약 0.06초 = 6,000,000
 * 		N*N * N*N * 2^M =(NXN 배열에서 좌표 2개 선택)*(M개중 0~M개를 선택하는 조합)		
 *     	10*10*10*10*2^5 = 320,000
 *     
 *     	(a,b)와 (b,a) 선택은 같으므로 해당 부분을 처리하면 /2 연산 가능
 *     	10*10*10*10*(/2)*2^5 = 160,000
 *     -> 시간 내에 가능
 *     
 *  2) 구현해야할 기능
 *  	벌통의 첫번째 인덱스를 2개 선택
 *      벌통안에 속해있는 모든 꿀의 합, 단 C보다 작아야함
*/
public class Solution {
	static int map[][]; // 벌꿀을 저장하는 배열
	static int M, C; // 꿀통의 크기, 최대 꿀크기
	static int maxHoney[]; // 농부 별 최대 꿀 수확 크기
	static int maxHoneySum; // 농부 별 최대 꿀 수확 크기의 합

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			M = Integer.valueOf(st.nextToken());
			C = Integer.valueOf(st.nextToken());

			map = new int[N][N];
			maxHoneySum = 0;

			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = Integer.valueOf(st.nextToken());
				}
			}

			pickBasket(new Coordinate[2], new Coordinate(0, -M), 0); // 시작점이 열+M이 0이 되도록 좌표 시작점 설정

			bw.write("#" + testcase + " " + maxHoneySum + "\n");
		}

		bw.flush(); // 결과 출력
		br.close();
		bw.close();
	}

	public static void pickBasket(Coordinate coords[], Coordinate start, int idx) { // 벌꿀통의 시작 인덱스를 고르는 메소드 (벌꿀 통 시작
																					// 좌표배열, 선택 시작 좌표, 농부 인덱스)
		if (idx == coords.length) {
			maxHoney = new int[2];

			pickHoney(coords[0].col, coords[0], 0, 0, new ArrayList<Coordinate>()); // 농부 1의 최대 값
			pickHoney(coords[1].col, coords[1], 0, 1, new ArrayList<Coordinate>()); // 농부 2의 최대값

			maxHoneySum = Math.max(maxHoneySum, maxHoney[0] + maxHoney[1]); // 최대 매출 합 최신화
		} else {
			for (int i = start.row; i < map.length; i++) { // 시작 좌표부터 선택
				for (int j = i == start.row ? start.col + M : 0; j + M - 1 < map[0].length; j++) { // 연속된 꿀통의 마지막 꿀통의 열값이 범위를 벗어나지 않을때 까지(행+ M-1<맵의 열크기)
					coords[idx] = new Coordinate(i, j);
					pickBasket(coords, coords[idx], idx + 1);
				}
			}
		}
	}

	public static void pickHoney(int colStart, Coordinate honey, int sum, int farmerIdx,
			ArrayList<Coordinate> selected) { // 시작 열, 현재 꿀 좌표, 꿀의 합, 농부 인덱스, 선택된 꿀의 좌표
		if (honey.col == colStart + M) {
			int squareSum = 0;
			for (Coordinate coord : selected) {	//선택된 꿀의 제곱의 합을 구하는 반복문
				squareSum += map[coord.row][coord.col] * map[coord.row][coord.col];
			}
			maxHoney[farmerIdx] = Math.max(squareSum, maxHoney[farmerIdx]);	//농부 별 꿀 최대값 갱신
		} else {
			Coordinate next = new Coordinate(honey.row, honey.col + 1);
			pickHoney(colStart, next, sum, farmerIdx, selected);	//현재 꿀을 선택하지 않음

			if (map[honey.row][honey.col] + sum <= C) {	//현재 꿀을 더했을때 최대 한계 C보다 작거나 같은경우(현재 꿀을 선택함)
				selected.add(new Coordinate(honey.row, honey.col));	//꿀 선택
				pickHoney(colStart, next, sum + map[honey.row][honey.col], farmerIdx, selected);
				selected.remove(selected.size() - 1);	//꿀 제거
			}
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