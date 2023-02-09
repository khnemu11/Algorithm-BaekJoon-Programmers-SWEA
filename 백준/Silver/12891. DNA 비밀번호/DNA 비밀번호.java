import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	A,C,G,T는 맵으로 알파벳 , 개수로 관리
	최소 개수의 합의 크기만큼 슬라이드 윈도우 적용
	초기화 : 인덱스 (0 ~ 최소개수의 합의 크기) 까지의 A,C,G,T 출현횟수
	루프 진행 : 왼쪽 포인터의 해당 알파벳 개수를 빼고 오른쪽 포인터+1의 해당 알파벳 개수를 추가
			 알파벳 개수가 최소조건을 만족하면 개수 추가
	루프 탈출 : 오른쪽 포인터 +1이 배열의 크기를 벗어날 때
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.valueOf(st.nextToken());
		int P = Integer.valueOf(st.nextToken());

		char str[] = br.readLine().toCharArray();

		HashMap<Character, Integer> conditionMap = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		// 최소 조건 등록
		conditionMap.put('A', Integer.valueOf(st.nextToken()));
		conditionMap.put('C', Integer.valueOf(st.nextToken()));
		conditionMap.put('G', Integer.valueOf(st.nextToken()));
		conditionMap.put('T', Integer.valueOf(st.nextToken()));

		// 부분 비밀번호 a,c,g,t 초기화
		HashMap<Character, Integer> passwordMap = new HashMap<>();
		passwordMap.put('A', 0);
		passwordMap.put('C', 0);
		passwordMap.put('G', 0);
		passwordMap.put('T', 0);

		int cnt = 0; // 만족하는 비밀번호 개수

		// 가장 왼쪽의 패스워드 판단
		for (int i = 0; i < P; i++) {
			passwordMap.put(str[i], passwordMap.get(str[i]) + 1);
		}

		// 가장 왼쪽의 패스워드가 조건에 만족하면 개수 증가
		if (isValid(conditionMap, passwordMap)) {
			cnt++;
		}

		int l = 1; // 다음 패스워드의 시작위치
		int r = P; // 다음 패스워드의 끝위치

		while (r < str.length) {
			passwordMap.put(str[l - 1], passwordMap.get(str[l - 1]) - 1);
			passwordMap.put(str[r], passwordMap.get(str[r]) + 1);

			if (isValid(conditionMap, passwordMap)) {
				cnt++;
			}

			r++;
			l++;
		}
		System.out.println(cnt);
	}

	// 조건 만족
	public static boolean isValid(HashMap<Character, Integer> conditionMap, HashMap<Character, Integer> passwordMap) {
		for (Character key : conditionMap.keySet()) {
			if (conditionMap.get(key) > passwordMap.get(key)) {
				return false;
			}
		}
		return true;
	}
}