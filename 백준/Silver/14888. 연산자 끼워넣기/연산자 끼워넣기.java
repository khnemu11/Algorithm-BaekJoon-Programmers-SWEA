import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int [] nums;
	static int [] op = new int[4];
	static int max;
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			op[i]=Integer.valueOf(st.nextToken());
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		findMaxMin(1,nums[0]);
		
		bw.write(String.valueOf(max));
		bw.newLine();
		bw.write(String.valueOf(min));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	public static void findMaxMin(int curr,int total) {
		if(curr==nums.length) {
			if(total>max) {
				max = total;
			}
			if(total<min) {
				min = total;
			}
		}
		
		else {
			for(int i=0;i<4;i++) {
				if(op[i]<=0)	continue;
				
				int temp = total;
				total = cal(i,curr,total);
				op[i]--;
				findMaxMin(curr+1,total);
				
				total=temp;
				op[i]++;
			}
		}
	}
	public static int cal(int i,int curr,int total) {
		if(i==0) {
			return total+nums[curr];
		}
		else if(i==1) {
			return total-nums[curr];
		}
		else if(i==2) {
			return total*nums[curr];
		}
		else if(i==3) {
			return total/nums[curr];
		}
		
		return -1;
	}
}
