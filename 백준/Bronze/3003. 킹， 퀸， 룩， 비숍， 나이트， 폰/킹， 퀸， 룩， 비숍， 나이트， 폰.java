import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(n->Integer.valueOf(n)).toArray();
		int [] correct = new int [] {1,1,2,2,2,8};
		int [] result = new int [6];
		for(int i=0;i<6;i++) {
			result[i] = correct[i]-num[i];
		}
		
		System.out.printf("%d %d %d %d %d %d",result[0],result[1],result[2],result[3],result[4],result[5]);
	}
}