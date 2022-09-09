import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] tmp ;
	static int count=0;
	static int target;
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int length = Integer.valueOf(st.nextToken());
		int []arr = new int[length];
		target = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		tmp = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		
		merge_sort(arr, 0, length-1);

		if(count<target) {
			System.out.println(-1);
		}
		
		bw.flush();
		br.close();
		bw.close();
	}

	public static void merge_sort(int[] a, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			merge_sort(a, p, q);
			merge_sort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}

	public static void merge(int a[], int p, int q, int r) {
		int i = p;
		int j = q + 1;
		int t = 0;
		while (i <= q && j <= r) {
			if (a[i] <= a[j]) {
				tmp[t++] = a[i++];
			} else
				tmp[t++] = a[j++];
		}
		while (i <= q) {
			tmp[t++] = a[i++];
		}

		while (j <= r) {
			tmp[t++] = a[j++];
		}
		i = p;
		t = 0;
		
		while (i <= r) {
			count++;
			if(count==target) {
				System.out.println(tmp[t]);
			}
			a[i++] = tmp[t++];
		}

	}

}