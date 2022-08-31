import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashMap<Integer,Integer> compression = new HashMap<>();
		int n = Integer.valueOf(br.readLine());
		
		Integer [] nums = new Integer[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			nums[i]=Integer.valueOf(st.nextToken());
		}
		
		Integer [] sorted = Arrays.copyOf(nums, nums.length);
		
		sorted = Arrays.stream(sorted).distinct().toArray(Integer[]::new);
		
		Arrays.sort(sorted);
		
		for(int i=0;i<sorted.length;i++) {
			compression.put(sorted[i], i);
		}
		
		for(int i=0;i<nums.length;i++) {
			bw.write(String.valueOf(compression.get(nums[i])));
			bw.write(" ");
		}
		
		bw.newLine();	
		
		bw.flush();
		bw.close();
		br.close();
	}
}
