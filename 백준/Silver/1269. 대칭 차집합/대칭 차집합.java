import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] bfsvisited;
	static boolean[] dfsvisited;
	static boolean[] existed;

	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int firstSize = Integer.valueOf(st.nextToken());
		int secondSize = Integer.valueOf(st.nextToken());

		Set<Integer> a = new HashSet<>();
		Set<Integer> b = new HashSet<>();

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < firstSize; i++) {
			a.add(Integer.valueOf(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		
		int count = 0;
		
		for (int i = 0; i < secondSize; i++) {
			int num = Integer.valueOf(st.nextToken());
			if (a.contains(num)) {
				count++;
			}
			b.add(num);
		}
		
		int result = a.size() - count + b.size() - count;
		
		bw.write(String.valueOf(result));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
		
	}

}
