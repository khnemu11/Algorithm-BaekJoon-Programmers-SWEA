import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
	
	public class Main {
		public static void main(String []args) throws IOException{	
			HashMap<String,Integer> list = new HashMap<String,Integer>();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int M =  Integer.valueOf(st.nextToken());
			String[] result = new String[M];
			int count = 0;
			
			for(int i=0;i<N;i++) {
				list.put(br.readLine(), 0);
			}
			
			for(int i=0;i<M;i++) {
				String name = br.readLine();
				if(list.containsKey(name)) {
					result[count]=name;
					count++;
				}
			}
			result= Arrays.stream(result).filter(x->x!=null).toArray(String[]::new);
			Arrays.sort(result);
			System.out.println(count);
			for(String name : result) {
				System.out.println(name);
			}
			
			br.close();
		}
	}