import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
 * 			1	2	3	4	5	6	7
 * 3,10		0	0	10	0	0	0	0
 * 5,20		0	0	10	0	0	20	0
 * 1,10		0	0	10	0	0	20	0
 * 1,20		0	0	10	30	0	20	0
 * 2,15		0	0	10	30	0	45	0
 * 
 * */
class Main {
	public static void main(String[] agrs) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] benefits = new int[N+1];
		int max = 0;
		
		for(int i=1; i<=N; i++) {
			String[] input = br.readLine().split(" ");
			benefits[i] = Math.max(benefits[i-1], benefits[i]);
			
			int time = Integer.parseInt(input[0]);
			int price = Integer.parseInt(input[1]);
			
			if(i + time - 1 > N) {
				continue;
			}
			
			benefits[i+time-1] = Math.max(benefits[i+time-1], benefits[i-1] + price);
			max = Math.max(max, benefits[i+time-1]);
		}
		
		System.out.println(max);
	}
}