import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
		public static void main(String []args) throws NumberFormatException, IOException{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			MyQueue myQueue = new MyQueue();
			
			int T = Integer.valueOf(br.readLine());
			
			for(int i=0;i<T;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
		
				if(command.equals("push")) {
					int value = Integer.valueOf(st.nextToken());
					
					myQueue.push(value);
				}
				else if(command.equals("pop")) {
					bw.write(String.valueOf(myQueue.pop()));
					bw.newLine();
				}
				else if(command.equals("size")) {
					bw.write(String.valueOf(myQueue.size()));
					bw.newLine();
				}
				else if(command.equals("empty")) {
					bw.write(String.valueOf(myQueue.empty()));
					bw.newLine();
				}
				else if(command.equals("front")) {
					bw.write(String.valueOf(myQueue.front()));
					bw.newLine();
				}
				else if(command.equals("back")) {
					bw.write(String.valueOf(myQueue.back()));
					bw.newLine();
				}
			}
			
			bw.flush();
			
			bw.close();
			br.close();
		}
}

class MyQueue{
	LinkedList<Integer> elements = new LinkedList<>();
	
	public MyQueue() {};
	public void push(int x) {
		elements.add(x);
	}
	public int pop() {
		int value=-1;
		
		if(!elements.isEmpty()) {
			value = elements.getFirst();
			elements.removeFirst();
		}
		
		return value;
	}
	public int size() {
		return elements.size();
	}
	public int empty() {
		if(elements.isEmpty()) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public int front() {
		if(elements.isEmpty()) {
			return -1;
		}
		else {
			return elements.getFirst();
		}
	}
	public int back() {
		if(elements.isEmpty()) {
			return -1;
		}
		else {
			return elements.getLast();
		}
	}
	
}
