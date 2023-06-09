package ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * n = 100,000 -> 최대 nlogn 알고리즘 필요
 * 동물의 좌표와 사대의 거리를 재서 사정거리 이내인지 확인
 * => 동물의 좌표 vs 사대의 거리를 비교
 * => 탐색
 * => 이분 탐색
 * 
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int X = 0;	//동물의 x 좌표
		int Y = 1; //동물의 Y 좌표

		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 사대의 수
		int N = Integer.parseInt(st.nextToken()); // 동물의 수
		int L = Integer.parseInt(st.nextToken()); // 사정거리
		
		//사대를 오름차순으로 정렬
		int shooters[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).sorted().toArray();

		int count = 0; //잡을 수 있는 동물의 수
		
		//이분탐색
		for (int i = 0; i < N; i++) {
			int coordinate[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			int l = 0;
			int r = M - 1;

			while (l <= r) {
				int mid = (l + r) / 2;
				//사정거리 이내에 든다면 개수 증가
				if (Math.abs(shooters[mid] - coordinate[X]) + coordinate[Y] <= L) {
					count++;
					break;
				} 
				//현재 동물의 좌표가 사대의 좌표 보다 크다면 현재 사대보다 작은 좌표는 불가능 하므로 최소 값 재설정
				else if (shooters[mid] < coordinate[X]) {
					l = mid + 1;
				} 
				//현재 동물의 좌표가 사대의 좌표 보다 작다면 현재 사대보다 큰 좌표는 불가능 하므로 최대 값 재설정
				else {
					r = mid - 1;
				}
			}
		}
		bw.write(count + "\n");
		bw.close();
	}
}
