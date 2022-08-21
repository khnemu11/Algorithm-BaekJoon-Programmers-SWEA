import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
	
	public class Main {
		public static void main(String []args) throws IOException{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int T = Integer.valueOf(br.readLine());
			
			for(int k=0;k<T;k++){
				int num = Integer.valueOf(br.readLine());
				boolean []primeList = new boolean[num+1];
				List<Integer> prime = new ArrayList<Integer>();
				
				primeList[0]=true;
				primeList[1]=true;
						
				for(int i=2;i<=num;i++) {
					if(primeList[i]==false) {
						prime.add(i);
						for(int j=i+i;j<=num;j=j+i) {
							primeList[j]=true;
						}
					}
				}
				
				int a=num,b=0;
				
				for(int i=0;i<prime.size();i++) {
					int findA = prime.get(i);
					int findB = num - findA;
					
					if(prime.contains(findB) && Math.abs(a-b) > Math.abs(findA-findB)) {
						a=findA;
						b=findB;
					}
				}
				
				System.out.println(a+" "+b);
			}
			
			br.close();
		}
	}