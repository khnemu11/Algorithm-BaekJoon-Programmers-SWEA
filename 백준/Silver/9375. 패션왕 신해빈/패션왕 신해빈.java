import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());
		//(옷의 종류(type)당 옷의 개수+1(안입기))*(옷의 종류(type)당 옷의 개수+1(안입기))*... -1(전부 안입기) 
		for (int i = 0; i < T; i++) {
			int n = Integer.valueOf(br.readLine());
			Clothes[] arr = new Clothes[n];
			Set<String> types = new HashSet<>();
			for (int j = 0; j < n; j++) {		
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String type = st.nextToken();
				arr[j] = new Clothes(name,type);
				types.add(type);
			}
			Map<String,List<Clothes>> clothesGroup = Arrays.stream(arr).collect(Collectors.groupingBy(Clothes::getType));
			
			int result = 1;
			Iterator<String> it = types.iterator();
			while(it.hasNext()) {
				result = result*(clothesGroup.get(it.next()).size()+1);
			}
			
			bw.write(String.valueOf(result-1));
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}
}

class Clothes {
	String name;
	String type;

	public Clothes(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getType() {
		return type;
	}

}