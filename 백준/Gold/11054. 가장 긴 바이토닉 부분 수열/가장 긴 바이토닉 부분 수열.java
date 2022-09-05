import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());
		int [] nums = new int [n];
		//n번째 에서 최대 증가하는 수열은 bottom-up 형식으로 진행한다.
		//n번째 수열인경우 0~n-1까지 확인하여 자신보다 숫자가 작으면서 0~n-1번째의 증가 수열에서 자신을 더한것이 자신에서 끝나는 증가수열보다 크면 값을 큰쪽으로 바꾸어준다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			nums[i]= Integer.valueOf(st.nextToken());
		}
		
		int increasingDp [] = new int [n];
		
		increasingDp[0]=1;
		for(int i=1;i<n;i++) {
			increasingDp[i]=1;
			for(int j=0;j<i;j++) {
				if(nums[j]<nums[i] && increasingDp[j]+1 > increasingDp[i]) {
					increasingDp[i] = increasingDp[j]+1;
				}
			}
		}
		//n번째 에서 감소 증가하는 수열은 top-down 형식으로 진행한다.
		//n번째 수열인경우 n~전체길이 -1 까지 역순으로 확인하여 자신보다 숫자가 작으면서 n-1~(전체길이 -1)번째의 증가 수열에서 자신을 더한것이 자신에서 끝나는 증가수열보다 크면 값을 큰쪽으로 바꾸어준다.
				
		int decreasingDp [] = new int [n];
		decreasingDp[n-1]=1;
		for(int i=n-2;i>=0;i--) {
			decreasingDp[i]=1;
			for(int j=n-1;j>i;j--) {
				if(nums[j]<nums[i] && decreasingDp[j]+1 > decreasingDp[i]) {
					decreasingDp[i] = decreasingDp[j]+1;
				}
			}
		}
		//바이토닉 부분수열 최대값은 0~k 까지의 증가수열 최대값 + k~n-1까지의 감소수열 최대값 -1(해당 위치가 중복되므로) 이다.
		int max = 0;
		for(int i=0;i<n;i++) {
			int val = increasingDp[i] + decreasingDp[i] -1;
			if(max < val) {
				max = val;
			}
		}
		
		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}