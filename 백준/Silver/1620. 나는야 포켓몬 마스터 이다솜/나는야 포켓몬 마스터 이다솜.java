import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int length = Integer.valueOf(st.nextToken());
		int questions = Integer.valueOf(st.nextToken());
		
		Map<Integer,String> noToName = new HashMap<>();
		Map<String,Integer> nameToNo = new HashMap<>();
		
		for(int i=1;i<=length;i++) {
			String name = br.readLine();
			noToName.put(i, name);
			nameToNo.put(name, i);
		}
		
		for(int i=0;i<questions;i++) {
			String question = br.readLine();
			
			if(question.matches("^[a-zA-Z]+$")) {
				System.out.println(nameToNo.get(question));
			}
			else {
				System.out.println(noToName.get(Integer.valueOf(question)));
			}
		}
		
		br.close();
	}
}