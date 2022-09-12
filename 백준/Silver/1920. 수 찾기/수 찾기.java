import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Set<Integer> set = new HashSet<Integer>();

		int size = Integer.valueOf(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < size; i++) {
			set.add(Integer.valueOf(st.nextToken()));
		}
		
		size = Integer.valueOf(br.readLine());

		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < size; i++) {
			if(set.contains(Integer.valueOf(st.nextToken()))) {
				bw.write("1");
			}
			else {
				bw.write("0");
			}
			bw.newLine();
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
