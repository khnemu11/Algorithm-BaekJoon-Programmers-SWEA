import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());

		for(int i=0;i<T;i++) {
			int [] input = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
			int height = input[2]%input[0]==0 ?  input[0]: input[2]%input[0];
			int weight =input[2]%input[0] == 0 ? input[2]/input[0]: input[2]/input[0]+1;
			
			System.out.println(height*100+weight);
		}
	}
}