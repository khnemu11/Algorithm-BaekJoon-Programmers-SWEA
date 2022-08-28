import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int N = Integer.valueOf(br.readLine());
			List<Integer> nums = new ArrayList<>();
			for(int i=0;i<N;i++) {
				nums.add(Integer.valueOf(br.readLine()));
			}
			
			Collections.sort(nums);
			//System.out.println(nums.toString());
			double average =nums.stream().collect(Collectors.averagingInt(x->x));
			int mid = nums.get(nums.size()/2);
			
			Map<Integer, Long> group = nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
			//System.out.println(group.toString());
			List<Integer> freq = new ArrayList<>();
			
			long freqMax=0;
			
		//	System.out.println(group.keySet().toString());
			for(int key : group.keySet()) {
			//	System.out.println(">> "+key);
				if(freqMax<group.get(key)) {
					freq.clear();
					freq.add(key);
					freqMax=group.get(key);
				}
				else if(freqMax == group.get(key)) {
					freq.add(key);
				}
			}
			
			long mostfreq = 0;
			if(freq.size()==1) {
				mostfreq = freq.get(0);
			}
			else {
				Collections.sort(freq);
				//System.out.println(freq.toString());
				mostfreq = freq.get(1);
			}
			
			int range = nums.stream().collect(Collectors.summarizingInt(x->x)).getMax()
					-nums.stream().collect(Collectors.summarizingInt(x->x)).getMin();
			
			bw.write(String.valueOf(Math.round(average)));
			bw.newLine();
			bw.write(String.valueOf(mid));
			bw.newLine();
			bw.write(String.valueOf(mostfreq));
			bw.newLine();
			bw.write(String.valueOf(range));
			bw.newLine();
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
