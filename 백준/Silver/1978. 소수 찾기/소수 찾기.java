import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
		int count=0;
		
		for(int i=0;i<N;i++) {
			count++;
			if(input[i]==1) count--;
			else {
				for(int j=2;j<input[i];j++) {
					if(input[i]%j==0) {
						count--;
						break;
					}
				}
			}
		}
		System.out.println(count);
	}
}
