import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.valueOf(br.readLine());
		int N = Integer.valueOf(br.readLine());
		int num [] = Stream.iterate(0, n->n+1).limit(N+1).mapToInt(n->Integer.valueOf(n)).toArray();
		
		 num[0]=0;
		 num[1]=0;
		 for(int i=2;i<=N;i++) {  
			 if(num[i]!=0) { 
				 for(int j=i*2;j<=N;j=j+i) { 
					 num[j]=0;
				 } 
			 } 
		 }
		 int [] primeNum =Arrays.stream(num).filter(n->n!=0 && n>=M).toArray();

		 if(primeNum.length>0) {
			 System.out.println(Arrays.stream(primeNum).sum());
			 System.out.println(primeNum[0]);
		 }
		 else {
			 System.out.println(-1);
		 }
		 
	}
}