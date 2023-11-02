import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
* 	걸린 시간 : 5분
* */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);

		int[] arr = new int[N];

		for(int i=0;i<arr.length;i++){
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int l=0,r=0;
		long min_differ = 10_000_000_001L;

		while(r < arr.length){
			long differ = arr[r] - arr[l];

			if(differ >= M && min_differ > differ ){
				min_differ = differ;
			}

			if(differ > M){
				l++;
			}else {
				r++;
			}
		}

		System.out.println(min_differ);
	}
}