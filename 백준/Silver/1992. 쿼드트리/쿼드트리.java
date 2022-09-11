import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] arr;

	public static void main(String arg[]) throws IOException {
		//전체가 모두 0인지 1인지 확인하고 아니라면 4구역으로 나누어서 다시 0인지 1인지 판단 -> 4개로 분할 정복
		//구역으로 나누어 질때만 괄호가 붙고 순서가 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 인것을 유의하면서 해결하자.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());

		arr = new int[size][size];

		for (int i = 0; i < size; i++) { //숫자 하나당 2차원 배열에 넣는 루프
			String row = br.readLine();
			for (int j = 0; j < size; j++) {
				arr[i][j] = row.charAt(j) - 48;  //0의 아스키 코드값인 48을 빼서 문자->숫자로 바꿈
			}
		}

		String result = quadTree(0, size, 0, size); //쿼드트리 실행

		bw.write(result);
		bw.newLine();
		br.close();
		bw.close();
	}

	public static String quadTree(int widthStart, int widthEnd, int heightStart, int heightEnd) { //가로 시작,가로 끝, 높이 시작, 높이 끝
		boolean next = false;
		boolean white = false;
		boolean black = false;

		if (widthStart == widthEnd || heightStart == heightEnd) { //크기가 0X0인경우 빈 문자열 리턴
			return "";
		}
 
		for (int i = heightStart; i < heightEnd; i++) { //각 구역의 모든 값을 탐색하여 모두 0인지, 1인지, 하나라도 다른지 확인
			for (int j = widthStart; j < widthEnd; j++) {
				if (arr[i][j] == 0 && !black) {
					white = true;
				} else if (arr[i][j] == 1 && !white) {
					black = true;
				} else {
					next = true;
					break;
				}
			}
			if (next) { //하나라도 다르면 루프 탈출
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		if (next) { //하나라도 다르면 괄호를 생성하고 각 구역의 순서대로 쿼드트리 재탐색
			sb.append("(");
			sb.append(quadTree(widthStart, (widthStart + widthEnd) / 2, heightStart, (heightStart + heightEnd) / 2));
			sb.append(quadTree((widthStart + widthEnd) / 2, widthEnd, heightStart, (heightStart + heightEnd) / 2));
			sb.append(quadTree(widthStart, (widthStart + widthEnd) / 2, (heightStart + heightEnd) / 2, heightEnd));
			sb.append(quadTree((widthStart + widthEnd) / 2, widthEnd, (heightStart + heightEnd) / 2, heightEnd));
			sb.append(")");
		} else { //모두 같은 숫자면 1 혹은 0을 출력
			if (black) {
				sb.append(1);
			} else {
				sb.append(0);
			}
		}

		return sb.toString();
	}
}