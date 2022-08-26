import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.valueOf(br.readLine());
		String [] serials = new String[N];
		for(int i=0;i<N;i++) {
			serials[i] = br.readLine();
		}
		
		Arrays.sort(serials,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length()==o2.length()) {
					int o1SubO2Count=0;
					for(int i=0;i<o1.length();i++) {
						if(Character.isDigit(o1.charAt(i))) {
							o1SubO2Count=o1SubO2Count+Character.getNumericValue(o1.charAt(i));
						}
					}
					for(int i=0;i<o2.length();i++) {
						if(Character.isDigit(o2.charAt(i))) {
							o1SubO2Count=o1SubO2Count-Character.getNumericValue(o2.charAt(i));
						}
					}
				//	System.out.println(o1+" vs "+o2+" = "+o1SubO2Count);
					
					if(o1SubO2Count==0) {
						return o1.compareTo(o2);
					}
					return o1SubO2Count;
				}
				else {
					return o1.length()-o2.length();
				}
			}
			
		});
		
		bw.write(Arrays.stream(serials).collect(Collectors.joining("\n","","")));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
