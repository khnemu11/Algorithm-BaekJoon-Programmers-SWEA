import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int target = Integer.valueOf(st.nextToken());
		int arr [] = new int [size];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<size;i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		int count=0;
		for(int i=0;i<size;i++) {
			int sum = 0;
			for(int j=i;j<size;j++) {
				sum=sum+arr[j];
				if(sum>=target) {
					if(sum == target) {
						count++;
					}
					break;
				}
			}
		}
		
		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
