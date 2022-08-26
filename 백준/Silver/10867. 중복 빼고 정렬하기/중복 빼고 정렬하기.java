import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		br.readLine();
		
		Integer [] numList = Arrays.stream(br.readLine().split(" ")).distinct().map(x->Integer.valueOf(x)).toArray(Integer[]::new);
		Arrays.sort(numList);
		
		bw.write(Arrays.stream(numList).map(x->String.valueOf(x)).collect(Collectors.joining(" ","","")));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
