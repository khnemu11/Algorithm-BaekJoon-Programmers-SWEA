import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int row = Integer.valueOf(st.nextToken());
		int column = Integer.valueOf(st.nextToken());
		String [] board= new String [row];
		List<Integer> reTouch = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			board[i] = br.readLine();
		}
		char black='B';
		char white ='W';

		for(int i=0;i+7<row;i++) {
			for(int j=0;j+7<column;j++) {
				char next =  board[i].charAt(j);
				int Wcount = 0;
				int Bcount=0;
				next = white;
				for(int w=0;w<8;w++) {
					for(int h=0;h<8;h++) {
						if(board[i+w].charAt(j+h)!=next) {
							Wcount++;
						}
						if(h!=7) {
							next = next == 'W' ? black : white;
						}
					}
				}
				next = black;
				for(int w=0;w<8;w++) {
					for(int h=0;h<8;h++) {
						if(board[i+w].charAt(j+h)!=next) {
							Bcount++;
						}
						if(h!=7) {
							next = next == 'W' ? black : white;
						}
					}
				}
				reTouch.add(Math.min(Wcount, Bcount));
			}
		}
		System.out.println(reTouch.stream().min(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.intValue()-o2.intValue();
			}
			
		}).get().intValue());
		
	
		br.close();
	}
}