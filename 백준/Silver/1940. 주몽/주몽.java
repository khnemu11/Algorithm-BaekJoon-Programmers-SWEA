import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	static final int WALL = 1;
	static final int EMPTY = 0;
	static final int INF = -1;
	
	public static void main(String[] agrs) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		String[] input = br.readLine().split(" ");
		
		Set<Integer> numSet = new HashSet<>();
		
		for(int i=0;i<input.length;i++) {
			numSet.add(Integer.parseInt(input[i]));
		}
		
		int count = 0;
		
		for(int num : numSet) {
			if(numSet.contains(M - num)) {
				count++;
			}
		}
		
		System.out.println(count/2);
	}
}