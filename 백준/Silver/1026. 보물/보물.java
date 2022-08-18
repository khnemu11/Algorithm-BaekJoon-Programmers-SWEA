import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		int a[] = new int[size];
		Integer b[] = new Integer[size];
		int result=0;
		
		String [] stringA = br.readLine().split(" ");
		

		for(int i = 0; i<size;i++) {
			a[i] = Integer.valueOf(stringA[i]);
			
		}
		
		String [] stringB = br.readLine().split(" ");
		for(int i = 0; i<size;i++) {
			b[i] = Integer.valueOf(stringB[i]);
			
		}
		Arrays.sort(a);
		Arrays.sort(b,Collections.reverseOrder());
		
		for(int i=0;i<size;i++) {
			result = result + a[i]*b[i];
		}
		
		System.out.println(result);
	}
}
