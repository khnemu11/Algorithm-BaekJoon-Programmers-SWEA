import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static long histogram[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			histogram = Arrays.stream(br.readLine().split(" ")).mapToLong(x -> Long.valueOf(x)).toArray();
			if (histogram[0] == 0) {
				break;
			}

			long max = getArea(1, histogram.length - 1);
			bw.write(String.valueOf(max) + "\n");
		}
		bw.flush();
	}

	public static long getArea(int left, int right) {
		if (left == right) {
			return histogram[left];
		}
		int mid = (left + right) / 2;

		long leftSize = getArea(left, mid);
		long rightSize = getArea(mid + 1, right);
		long max = Math.max(leftSize, rightSize);

		long midSize = getMidArea(left, mid, right);
		max = Math.max(max, midSize);
		return max;
	}

	public static long getMidArea(int left, int mid, int right) {
		long max = histogram[mid];
		long height = histogram[mid];
		int lp = mid, rp = mid;
		long currArea = 0;
		while (lp > left && rp < right) {
			if (histogram[lp - 1] > histogram[rp + 1]) {
				lp--;
				height = Math.min(histogram[lp], height);
				currArea = (rp - lp + 1) * height;
			} else {
				rp++;
				height = Math.min(histogram[rp], height);
				currArea = (rp - lp + 1) * height;
			}

			max = Math.max(max, currArea);
		}

		while (lp > left) {
			lp--;
			height = Math.min(histogram[lp], height);
			currArea = (rp - lp + 1) * height;
			max = Math.max(max, currArea);
		}
		while (rp < right) {
			rp++;
			height = Math.min(histogram[rp], height);
			currArea = (rp - lp + 1) * height;
			max = Math.max(max, currArea);
		}

		return max;
	}
}
