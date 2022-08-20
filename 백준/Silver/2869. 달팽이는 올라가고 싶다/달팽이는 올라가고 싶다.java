import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int [] input = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
		int result = (int )Math.ceil(((double)input[2]-input[0])/(double)(input[0]-input[1])+1);
		System.out.println(result);
	}
}
