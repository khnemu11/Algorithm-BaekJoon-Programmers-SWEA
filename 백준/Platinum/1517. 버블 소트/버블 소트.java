import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long count;
	static int sorted[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int length = Integer.valueOf(br.readLine());
		sorted = new int[length];
		int arr[] = new int[length];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < length; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		mergeSort(0,length-1,arr);
		
		bw.write(String.valueOf(count));
		br.close();
		bw.close();
	}

	public static void merge(int left, int right, int mid, int[] arr) {
		int leftIndex = left;
		int rightIndex = mid + 1;
		int sortedIndex = left;

		while (leftIndex <= mid && rightIndex <= right) {
			if (arr[leftIndex] > arr[rightIndex]) {
				sorted[sortedIndex] = arr[rightIndex];
				sortedIndex++;
				rightIndex++;
				count += (long) mid - leftIndex + 1;
			} else {
				sorted[sortedIndex] = arr[leftIndex];
				sortedIndex++;
				leftIndex++;
			}
		}
		if (leftIndex <= mid) {
			while (leftIndex <= mid) {
				sorted[sortedIndex] = arr[leftIndex];
				sortedIndex++;
				leftIndex++;
			}
		} else {
			while (rightIndex <= right) {
				sorted[sortedIndex] = arr[rightIndex];
				sortedIndex++;
				rightIndex++;

			}
		}
		for (int i = left; i <= right; i++) {
			arr[i] = sorted[i];
		}
	}

	public static void mergeSort(int left, int right, int[] arr) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid, arr);
			mergeSort(mid + 1, right, arr);
			merge(left, right, mid, arr);
		}
	}
}
