import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] status;
	static boolean[] visited;
	
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());
		status = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				status[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		findMin(0,1);

		bw.write(String.valueOf(min));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}

	public static void findMin(int startIndex,int depth) {
		if (depth > status.length/2) {
			//System.out.println(Arrays.toString(visited));
			
			int startTotal=0;
			for(int i=0;i<status.length;i++) {
				if(visited[i]) {
					for(int j=0;j<status.length;j++) {
						if(visited[j]) {
					//		System.out.print(status[i][j]+" ");
							startTotal+=status[i][j];
						}	
					}
				}
				
			}
		//	System.out.println();
			int linkTotal=0;
			for(int i=0;i<status.length;i++) {
				if(!visited[i]) {
					for(int j=0;j<status.length;j++) {
						if(!visited[j]) {
			//				System.out.print(status[i][j]+" ");
							linkTotal+=status[i][j];
						}
					}
				}
				
			}
		//	System.out.println();
		//	System.out.println(startTotal+" "+linkTotal);
			
			int differ = Math.abs(startTotal-linkTotal);

			if(min>differ) {
				min=differ;
			}
		}

		else {
			for (int i = startIndex; i < status.length; i++) {
				if (visited[i]) {
					continue;
				}
				visited[i] = true;
				
				findMin(i+1,depth + 1);

				visited[i] = false;
			}
		}
	}
}
