import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.valueOf(br.readLine());
		
		String [] words = new String[n];
		
		for (int i = 0; i < n; i++) {
			words[i]=br.readLine();
		}
		
		words = Arrays.stream(words).distinct().toArray(String[]::new);
		
		Arrays.sort(words,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length()==o2.length()) {
					return o1.compareTo(o2);
				}
				else {
					return o1.length()-o2.length();
				}
				
			}
			
		});	
		
		for(String word : words) {
			StringBuilder sb =new StringBuilder();
			
			sb.append(word);
			sb.append("\n");
			
			bw.write(sb.toString());
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
