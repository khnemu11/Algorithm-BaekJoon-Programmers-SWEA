import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int M = Integer.valueOf(st.nextToken());
			int [] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
			int result=0;
			
			for(int i=0;i<nums.length-2;i++) {
				for(int j=i+1;j<nums.length-1;j++) {
					for(int k=j+1;k<nums.length;k++) {
						int sum = nums[i]+nums[j]+nums[k];
						if(sum <=M && result < sum) {
							result = sum;
						}
					}
				}
			}
			
			bw.write(String.valueOf(result));
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
