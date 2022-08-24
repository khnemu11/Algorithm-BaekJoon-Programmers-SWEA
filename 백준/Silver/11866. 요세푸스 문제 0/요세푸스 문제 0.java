import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
		public static void main(String []args) throws NumberFormatException, IOException{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int K = Integer.valueOf(st.nextToken());
			int turn = 0;
			
			List<Integer> circle = Stream.iterate(1, x->x+1).limit(N).collect(Collectors.toList());
			List<Integer> result = new ArrayList<>();
			
			for(int i=0;i<N;i++) {
				turn = turn + K -1;
				//System.out.println("> length : "+(N-i));
				while(turn >=N-i) {
					turn = turn-(N-i);
					//System.out.println(">> turn : "+turn);
				}
				
				result.add(circle.get(turn));
				circle.remove(turn);
				//System.out.println(circle.toString());
				//System.out.println("last turn : "+turn);
			}
			String resultString = result.stream().map(x->String.valueOf(x)).collect(Collectors.joining(", ","<",">"));
			
			bw.write(resultString);
			
			bw.flush();
			
			bw.close();
			br.close();
		}
}