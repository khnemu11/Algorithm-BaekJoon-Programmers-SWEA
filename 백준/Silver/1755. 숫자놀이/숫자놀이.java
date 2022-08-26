import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String [] numToEn = {"zero","one","two","three","four","five","six","seven","eight","nine"};
		
		int from = Integer.valueOf(st.nextToken());
		int to = Integer.valueOf(st.nextToken());
	
		String [] numList = IntStream.range(from, to+1).boxed().map(x->String.valueOf(x)).toArray(String[]::new);
		Arrays.sort(numList,new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				int min = Math.min(o1.length(),o2.length());
				StringBuilder o1Total = new StringBuilder();
				StringBuilder o2Total = new StringBuilder();
				for(int i=0;i<min;i++) {
					String o1Num = numToEn[Character.getNumericValue(o1.charAt(i))];
					String o2Num = numToEn[Character.getNumericValue(o2.charAt(i))];
					
					o1Total.append(o1Num);
					o2Total.append(o2Num);
				}
				
				return o1Total.compareTo(o2Total);
			}
			
		});
		
				
		for(int i=0;i<numList.length;i++) {
			bw.write(String.valueOf(numList[i]));
			
			if((i+1)%10==0 && i!=numList.length-1) {
				bw.write("\n");
			}
			else {
				bw.write(" ");
			}
		}
		                                 
		
		bw.flush();
		bw.close();
		br.close();
	}
}
