import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		Meeting [] meetings = new Meeting[N]; 

		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long start = Integer.parseInt(st.nextToken());
			long end = Integer.parseInt(st.nextToken());
			
			meetings[i] = new Meeting(start,end);
		}
		Arrays.sort(meetings);
		long next = meetings[0].end;
		int count = 1;
        
		for(int i=1;i<N;i++) {
			if(next <= meetings[i].start) {
				next = meetings[i].end;
				count++;
			}
		}	
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}

}
class Meeting implements Comparable<Meeting>{
	long start;
	long end;
	
	public Meeting(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Meeting o) {
		if(o.end == this.end) {
			return (int) (this.start - o.start);
		}
		else {
			return (int) (this.end - o.end);
		}
	}

	@Override
	public String toString() {
		return "Meeting [start=" + start + ", end=" + end + "]";
	}
	
}