
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> primes = new ArrayList<>();
		ArrayList<Long> sums = new ArrayList<>();

		primes.add(0);
		sums.add((long) 0);

		int N = Integer.valueOf(br.readLine());
		boolean nums[] = new boolean[N + 1];
		for (int i = 2; i <= N; i++) {
			if (nums[i]) {
				continue;
			}
			primes.add(i);
			sums.add(i + sums.get(sums.size() - 1));
			for (int j = i + i; j <= N; j += i) {
				nums[j] = true;
			}
		}

		int l = 1;
		int r = 1;
		int cnt = 0;
		while (l > 0 && r < primes.size() && l <= r) {
			long sum = sums.get(r) - sums.get(l) + primes.get(l);
			if (sum == N) {
				cnt++;
				l++;
			} else if (sum > N) {
				l++;
				r = l;
			} else {
				r++;
			}
		}

		System.out.println(cnt);
	}

}