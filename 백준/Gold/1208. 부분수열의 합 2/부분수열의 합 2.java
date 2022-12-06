import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static long num[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int size = row[0];
		int goal = row[1];

		num = new long[size];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < size; i++) {
			num[i] = Long.valueOf(st.nextToken());
		}

		ArrayList<Long> leftList = new ArrayList<>();
		ArrayList<Long> rightList = new ArrayList<>();

		findSubSequence(0, size / 2, 0, leftList);
		findSubSequence(size / 2, size, 0, rightList);

		Collections.sort(leftList);
		Collections.sort(rightList);

		long result = 0;
		int leftp = 0;
		int rightp = rightList.size() - 1;

		while (leftp < leftList.size() && rightp >= 0) {
			long left = leftList.get(leftp);
			long right = rightList.get(rightp);
			long sum = left + right;

			if (sum == goal) {
				long leftCount = 0;
				while (leftp < leftList.size() && leftList.get(leftp) == left) {
					leftCount++;
					leftp++;
				}

				long rightCount = 0;
				while (rightp >= 0 && rightList.get(rightp) == right) {
					rightCount++;
					rightp--;
				}

				result += leftCount * rightCount;
			} else if (sum < goal) {
				leftp++;
			} else {
				rightp--;
			}

		}

		if (goal == 0 && result > 0) {
			result--;
		}

		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();
	}

	public static void findSubSequence(int curr, int depth, long sum, ArrayList<Long> list) {
		if (curr == depth) {
			list.add(sum);
			
			return;
		} else {
			findSubSequence(curr + 1, depth, sum + num[curr], list);
			findSubSequence(curr + 1, depth, sum, list);
		}
	}
}
