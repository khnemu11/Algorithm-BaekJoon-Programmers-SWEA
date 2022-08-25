import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int []score = new int[8];
			int count =0;
			
			for(int i=0;i<8;i++) {
				score[i]=Integer.valueOf(br.readLine());
			}
			int[] oriScore = Arrays.copyOf(score, score.length);
			
			Arrays.sort(score);
			Integer [] topScore = Arrays.stream(Arrays.copyOfRange(score, 3,8)).boxed().toArray(Integer[]::new);
			
			System.out.println(Arrays.stream(topScore).mapToInt(Integer::intValue).sum());
			
			for(int i=0;i<8;i++) {
				if(Arrays.asList(topScore).contains(oriScore[i])) {
					System.out.print(i+1);
					count++;
					if(count==5)	break;
					else {
						System.out.print(" ");
					}
				}
			}
		}
}
