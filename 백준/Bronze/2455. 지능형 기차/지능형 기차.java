import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max=0;
		int count=0;
		for(int i=0;i<4;i++) {
			String [] num = br.readLine().split(" ");
			count=count- Integer.valueOf(num[0])+Integer.valueOf(num[1]);
			if(max<count) {
				max = count;
			}
		}
		System.out.println(max);
	}
}
