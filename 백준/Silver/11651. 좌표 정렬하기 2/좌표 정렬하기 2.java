import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.valueOf(br.readLine());
		
		Coordinate [] cooidinates = new Coordinate[n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cooidinates[i]=new Coordinate(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()));
		}
		
		Arrays.sort(cooidinates);
		
		for(Coordinate element : cooidinates) {
			StringBuilder sb = new StringBuilder();
			sb.append(element.x);
			sb.append(" ");
			sb.append(element.y);
			sb.append("\n");
			
			bw.write(sb.toString());
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
class Coordinate implements Comparable<Coordinate>{
	int x;
	int y;
	
	public Coordinate(int x,int y){
		this.x=x;
		this.y=y;
	}


	@Override
	public int compareTo(Coordinate o) {
		// TODO Auto-generated method stub
		if(this.y==o.y) {
			return this.x-o.x;
		}
		else return this.y-o.y;
	}
}