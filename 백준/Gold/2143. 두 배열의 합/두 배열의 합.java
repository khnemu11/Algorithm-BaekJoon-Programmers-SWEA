import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());
		int aLength = Integer.valueOf(br.readLine());
		int a[] = new int[aLength];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.valueOf(st.nextToken());
		}
		ArrayList<Integer> aSumList = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			int sum = 0;
			for (int j = i; j < a.length; j++) {
				sum += a[j];
				aSumList.add(sum);
			}
		}
		Collections.sort(aSumList);

		int bLength = Integer.valueOf(br.readLine());
		int b[] = new int[bLength];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < b.length; i++) {
			b[i] = Integer.valueOf(st.nextToken());
		}

		ArrayList<Integer> bSumList = new ArrayList<>();
		for (int i = 0; i < b.length; i++) {
			int sum = 0;
			for (int j = i; j < b.length; j++) {
				sum += b[j];
				bSumList.add(sum);
			}
		}
		Collections.sort(bSumList);

		long cnt = 0;
		int aIdx = 0;
		int bIdx = bSumList.size() - 1;

		while (aIdx < aSumList.size() && bIdx >= 0) {
			long sum = aSumList.get(aIdx) + bSumList.get(bIdx);
			if (sum == T) {

				int currA = aSumList.get(aIdx);
				int currB = bSumList.get(bIdx);
				long aCnt = 0;
				long bCnt = 0;
				while (true) {
					if (aIdx < aSumList.size() && currA == aSumList.get(aIdx)) {
						aCnt++;
						aIdx++;
					} else {
						break;
					}
				}

				while (true) {
					if (bIdx >= 0 && currB == bSumList.get(bIdx)) {
						bCnt++;
						bIdx--;
					} else {
						break;
					}
				}
				cnt += aCnt * bCnt;
			} else if (sum > T) {
				bIdx--;

			} else {
				aIdx++;
			}
		}
		System.out.println(cnt);
	}
}
