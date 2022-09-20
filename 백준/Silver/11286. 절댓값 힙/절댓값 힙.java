import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());
		PriorityQueue<Abs> minheap = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			int command = Integer.valueOf(br.readLine());
			
			if(command == 0) {
				if(minheap.isEmpty()) {
					bw.write("0");
				}
				else {
					bw.write(String.valueOf(minheap.poll().value));	
				}
				bw.newLine();
			}
			else {
				minheap.add(new Abs(command));
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
class Abs implements Comparable<Abs>{
	int value;


	public Abs(int value) {
		super();
		this.value = value;
	}


	@Override
	public int compareTo(Abs o) {
		if(Math.abs(this.value) == Math.abs(o.value)) {
			return this.value - o.value;
		}
		
		return Math.abs(this.value) - Math.abs(o.value);
	}
}