
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		LinkedHashMap<String, Integer> numMap = new LinkedHashMap<>();
		numMap.put("ZRO", 0);
		numMap.put("ONE", 1);
		numMap.put("TWO", 2);
		numMap.put("THR", 3);
		numMap.put("FOR", 4);
		numMap.put("FIV", 5);
		numMap.put("SIX", 6);
		numMap.put("SVN", 7);
		numMap.put("EGT", 8);
		numMap.put("NIN", 9);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			String prefix = sc.next();
			int N = sc.nextInt();
			int num[] = new int[10];
			
			for (int i = 0; i < N; i++) {
				num[numMap.get(sc.next())]++;
			}

			StringBuilder sb = new StringBuilder(prefix);
			sb.append("\n");

			Iterator<String> it = numMap.keySet().iterator();

			while (it.hasNext()) {
				String element = it.next();

				for (int i = 0; i < num[numMap.get(element)]; i++) {
					sb.append(element).append(" ");
				}
			}

			System.out.println(sb.toString());
		}

	}
}
