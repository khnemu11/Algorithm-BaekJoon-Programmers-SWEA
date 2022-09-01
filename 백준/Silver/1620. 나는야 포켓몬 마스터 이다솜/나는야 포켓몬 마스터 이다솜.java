import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int length = Integer.valueOf(st.nextToken());
		int questions = Integer.valueOf(st.nextToken());
		
		String[] noToName = new String[length+1];
		Map<String,Integer> nameToNo = new HashMap<>();
		
		for(int i=1;i<=length;i++) {
			String name = br.readLine();
			noToName[i]=name;
			nameToNo.put(name, i);
		}
		
		for(int i=0;i<questions;i++) {
			String question = br.readLine();
			
			if(question.matches("^[a-zA-Z]+$")) {
				bw.write(String.valueOf(nameToNo.get(question)));
			}
			else {
				bw.write(noToName[Integer.valueOf(question)]);
			}
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}
}