import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashMap<Integer,Integer> map = new HashMap<>();
		
		int T = Integer.valueOf(br.readLine());
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		for(int i=0;i<T;i++) {
			int key = Integer.valueOf(st.nextToken());
			map.put(key,map.getOrDefault(key, 0)+1);
		}
				
		bw.write(String.valueOf(map.getOrDefault(Integer.valueOf(br.readLine()),0)));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
