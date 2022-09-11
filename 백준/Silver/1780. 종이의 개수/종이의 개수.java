import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int num[];

	public static void main(String arg[]) throws IOException {
		// 전체가 모두 -1,0,1인지 확인하고 아니라면 9구역으로 나누어서 다시 -1,0,1 인지 판단 -> 9개로 분할 정복
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());

		arr = new int[size][size];
		num = new int[3];

		for (int i = 0; i < size; i++) { // 숫자 하나당 2차원 배열에 넣는 루프
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		nineDivide(0, size, 0, size); //분할 정복 실행

		for (int i = 0; i < 3; i++) {
			bw.write(String.valueOf(num[i]));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	public static void nineDivide(int widthStart, int widthEnd, int heightStart, int heightEnd) { // 가로 시작,가로 끝, 높이
																									// 시작, 높이 끝
		boolean next = false;
		boolean minus1 = false;
		boolean zero = false;
		boolean plus1 = false;

		if (widthStart == widthEnd || heightStart == heightEnd) { // 크기가 0X0인경우 빈 문자열 리턴
			return;
		}

		for (int i = heightStart; i < heightEnd; i++) { // 각 구역의 모든 값을 탐색하여 모두 0인지, 1인지, -1안지, 하나라도 다른지 확인
			for (int j = widthStart; j < widthEnd; j++) {
				if (arr[i][j] == -1 && !zero && !plus1) {
					minus1 = true;
				} else if (arr[i][j] == 0 && !minus1 && !plus1) {
					zero = true;
				} else if (arr[i][j] == 1 && !zero && !minus1) {
					plus1 = true;
				} else {
					next = true;
					break;
				}
			}
			if (next) { // 하나라도 다르면 루프 탈출
				break;
			}
		}
		if (next) { // 하나라도 각 구역의 순서대로 재탐색
			nineDivide(widthStart, widthStart + (widthEnd - widthStart) / 3, heightStart,
					heightStart + (heightEnd - heightStart) / 3);
			nineDivide(widthStart + (widthEnd - widthStart) / 3, widthStart + (widthEnd - widthStart) / 3 * 2,
					heightStart, heightStart + (heightEnd - heightStart) / 3);
			nineDivide(widthStart + (widthEnd - widthStart) / 3 * 2, widthEnd, heightStart,
					heightStart + (heightEnd - heightStart) / 3);

			nineDivide(widthStart, widthStart + (widthEnd - widthStart) / 3,
					heightStart + (heightEnd - heightStart) / 3, heightStart + (heightEnd - heightStart) / 3 * 2);
			nineDivide(widthStart + (widthEnd - widthStart) / 3, widthStart + (widthEnd - widthStart) / 3 * 2,
					heightStart + (heightEnd - heightStart) / 3, heightStart + (heightEnd - heightStart) / 3 * 2);
			nineDivide(widthStart + (widthEnd - widthStart) / 3 * 2, widthEnd,
					heightStart + (heightEnd - heightStart) / 3, heightStart + (heightEnd - heightStart) / 3 * 2);

			nineDivide(widthStart, widthStart + (widthEnd - widthStart) / 3,
					heightStart + (heightEnd - heightStart) / 3 * 2, heightEnd);
			nineDivide(widthStart + (widthEnd - widthStart) / 3, widthStart + (widthEnd - widthStart) / 3 * 2,
					heightStart + (heightEnd - heightStart) / 3 * 2, heightEnd);
			nineDivide(widthStart + (widthEnd - widthStart) / 3 * 2, widthEnd,
					heightStart + (heightEnd - heightStart) / 3 * 2, heightEnd);

		} else { // 모두 같은 숫자면 1 ,-1, 0 개수를 올려줌
			if (minus1) {
				num[0]++;
			} else if (zero) {
				num[1]++;
			} else {
				num[2]++;
			}
		}
	}
}