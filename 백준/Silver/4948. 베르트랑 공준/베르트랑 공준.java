import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
	public class Main {
		public static void main(String []args) throws IOException{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			for(int num = Integer.valueOf(br.readLine());num>0;num = Integer.valueOf(br.readLine())){
				boolean []primeList = new boolean[num*2+1];
				int count =0;
				
				primeList[0]=true;
				primeList[1]=true;
						
				for(int i=2;i*i<=num*2;i++) {
					if(primeList[i]==false) {
						for(int j=i+i;j<=num*2;j=j+i) {
							primeList[j]=true;
						}
					}
				}
				
				for(int i=0;i<=num;i++) {
					primeList[i]=true;
				}
				
				for(int i=num+1;i<=2*num;i++) {
					if(!primeList[i]) {
						count++;
					}
				}
				
				System.out.println(count);
			}
			
			br.close();
		}
	}