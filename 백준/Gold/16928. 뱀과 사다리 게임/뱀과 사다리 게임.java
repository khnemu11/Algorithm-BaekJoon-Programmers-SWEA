	
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.OutputStreamWriter;
	import java.util.HashMap;
	import java.util.LinkedList;
	import java.util.Queue;
	import java.util.StringTokenizer;
	
	public class Main {
		static boolean visited[];
	
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			visited = new boolean[101];
			HashMap<Integer, Integer> map = new HashMap<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
	
			int size = Integer.valueOf(st.nextToken()) + Integer.valueOf(st.nextToken());
	
			for (int i = 0; i < size; i++) {
				st = new StringTokenizer(br.readLine());
				map.put(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
			}
	
			Queue<Integer> queue = new LinkedList<>();
			queue.add(1);
			int min = bfs(map, 0, queue);
			bw.write(String.valueOf(min));
			bw.newLine();
			bw.flush();
			bw.close();
			br.close();
		}
	
		public static int bfs(HashMap<Integer, Integer> map, int count, Queue<Integer> queue) {
			Queue<Integer> next = new LinkedList<>();
			while (!queue.isEmpty()) {
				int curr = queue.poll();

				if (curr == 100) {
					return count;
				}
				for (int i = 1 + curr; i <= 6 + curr; i++) {
					if (i > 100) {
						break;
					} else if (!visited[i]) {
//						for (int k = 0; k < count; k++) {
//							System.out.print(" ");
//						}
//						System.out.print("No. "+count + " curr : ");
//						System.out.print(curr+" next : ");
						visited[i] = true;
						if(map.get(i)!=null) {
//							System.out.println(i + " -> "+map.get(i));
							visited[map.get(i)] = true;
							next.add(map.get(i));
						}
						else {
//							System.out.println(i);
							next.add(i);
						}
						
					}
				}
			}
	
			return bfs(map, count + 1, next);
	
		}
	}
