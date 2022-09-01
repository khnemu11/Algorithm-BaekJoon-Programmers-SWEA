import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder pattern = new StringBuilder();
		int weight = Integer.valueOf(br.readLine());
		int[] lengths = new int[6];
		int[] heightWidths = new int[4];
		for(int i=0; i<6;i++) {
			st = new StringTokenizer(br.readLine());
			String direction = st.nextToken();
			pattern.append(direction);
			
			lengths[i] = Integer.valueOf(st.nextToken());
			heightWidths[Integer.valueOf(direction)-1] += lengths[i];
		}
		pattern.append(pattern.toString());
	//	System.out.println(pattern.toString());
	///	System.out.println(Arrays.toString(heightWidths));
	//	System.out.println(Arrays.toString(lengths));
		
		String route = pattern.toString();
		
		int subx=0,suby=0;
		
		
		for(int i=0;i<route.length()-3;i++) {
//			System.out.println(route.substring(i, i+2)+" vs "+route.substring(i+2, i+4));
			if(route.substring(i, i+2).equals(route.substring(i+2, i+4))) {
//				System.out.println((i+1) + " "+(i+2));
				subx = i+1 < lengths.length	? i+1 : i+1 - lengths.length;
				suby = i+2 < lengths.length	? i+2 : i+2 - lengths.length;;
				break;
			}
		}
	//	System.out.println(lengths[subx]+" "+lengths[suby]);
		int [] heightWidth = Arrays.stream(heightWidths).distinct().toArray();
	//	System.out.println(Arrays.toString(heightWidth));
		int totalArea=0;
		
		if(heightWidth.length==1) {
			totalArea = heightWidth[0]*heightWidth[0];
		}
		else {
			totalArea = heightWidth[0]*heightWidth[1];
		}
		
		int result = (totalArea-(lengths[subx]*lengths[suby]))*weight;
		
		System.out.println(result);
		
		bw.flush();
		br.close();
		bw.close();
	}
}