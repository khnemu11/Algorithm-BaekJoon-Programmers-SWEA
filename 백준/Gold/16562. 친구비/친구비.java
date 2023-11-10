import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
	static int[] parents;
	static long[] costs;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);

		costs = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		parents = new int[N];

		for(int i=0;i<parents.length;i++){
			parents[i] = i;
		}

		for(int i=0;i<M;i++){
			input = br.readLine().split(" ");
			int v = Integer.parseInt(input[0])-1;
			int w = Integer.parseInt(input[1])-1;

			union(v,w);
		}

		boolean visited[] = new boolean[N];
		long sum = 0;

		for(int i=0;i<parents.length;i++){
			int parent = getParent(i);

			if(visited[parent]){
				continue;
			}
			visited[parent] = true;
			sum += costs[parent];
		}

		if(sum <= K){
			System.out.println(sum);
		}else{
			System.out.println("Oh no");
		}
	}

	public static int getParent(int child){
		if(parents[child] == child){
			return parents[child];
		}

		parents[child] = getParent(parents[child]);

		return parents[child];
	}

	public static void union(int a, int b){
		int pa = getParent(a);
		int pb = getParent(b);

		if(costs[pa] < costs[pb]){
			parents[pb] = pa;
		}else{
			parents[pa] = pb;
		}
	}
}