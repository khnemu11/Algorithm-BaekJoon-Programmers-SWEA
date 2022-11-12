/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
	Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		HashMap<String, Integer> map = new HashMap<>();

		map.put("0001101", 0);
		map.put("1110010", 0);
		map.put("0011001", 1);
		map.put("1100110", 1);
		map.put("0010011", 2);
		map.put("1101100", 2);
		map.put("0111101", 3);
		map.put("1000010", 3);
		map.put("0100011", 4);
		map.put("1011100", 4);
		map.put("0110001", 5);
		map.put("1001110", 5);
		map.put("0101111", 6);
		map.put("1010000", 6);
		map.put("1000100", 7);
		map.put("0111011", 7);
		map.put("0110111", 8);
		map.put("1001000", 8);
		map.put("0001011", 9);
		map.put("1110100", 9);

		for (int test_case = 1; test_case <= T; test_case++) {
			int height = sc.nextInt();
			int width = sc.nextInt();

			String row = sc.next();
			StringBuilder sb = new StringBuilder();
			StringBuilder result = new StringBuilder("#" + test_case + " ");
			int rest = height - 1;
			int num[] = new int[8];
			int numindex = 0;

			while (!row.contains("1")) {
				row = sc.next();
				rest--;
			}
			for (int i = 0; i < width - 7; i++) {

				if (numindex == 8) {
					break;
				}
				if (map.get(row.substring(i, i + 7)) != null) {
					num[numindex] = map.get(row.substring(i, i + 7));
					numindex++;
					i += 6;
				} else {
					i = i - numindex * 7;
					numindex = 0;
				}
			}
			int oddSum = 0;
			int evenSum = 0;

			for (int i = 0; i < num.length; i++) {
				sb.append(num);
				if (i % 2 == 0) {
					evenSum += num[i];
				} else {
					oddSum += num[i];
				}
			}

			if ((oddSum + evenSum * 3) % 10 == 0) {
				result.append(evenSum + oddSum);
			} else {
				result.append(0);
			}
			System.out.println(result.toString());

			while (rest-- > 0) {
				sc.next();
			}
		}
	}
}