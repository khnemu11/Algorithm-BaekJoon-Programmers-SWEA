import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] sudoku = new int[9][9];
	static List<Integer> emptyX = new ArrayList<>();;
	static List<Integer> emptyY = new ArrayList<>();;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.valueOf(st.nextToken());
				if (sudoku[i][j] == 0) {
					emptyX.add(i);
					emptyY.add(j);
				}
			}
		}
		
	//	System.out.println(emptyX.toString());
	//	System.out.println(emptyY.toString());
		
		check(0);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				bw.write(String.valueOf(sudoku[i][j]));
				bw.write(" ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean check(int depth) {
		if(depth==emptyX.size()) {
		//	System.out.println("find!!");
			return true;
		}
		
		else {
	//		System.out.println("check ( " +emptyX.get(depth)+" , "+emptyY.get(depth)+" )");
			
			for(int i=1;i<10;i++) {
				//조건 확인
				if(!possible(i,depth)) {
					continue;
				}
				//다음것도 맞으면 계속하고 아니면 그만
				sudoku[emptyX.get(depth)][emptyY.get(depth)] = i;
				if(check(depth+1)) {
					return true;
				}
				sudoku[emptyX.get(depth)][emptyY.get(depth)] = 0;
			}
			
		}		
		return false;
	}
	
	public static boolean possible(int value,int depth) {
		
		//가로
	//	System.out.println("row check");
		for(int i=0;i<9;i++) {
			
			if(sudoku[emptyX.get(depth)][i]==value) {
		//		System.out.println(sudoku[emptyX.get(depth)][i]+" : "+value);
		//		System.out.println("duplicate row");
				return false;
			}
		}
		//세로
//		System.out.println("column check");
		for(int i=0;i<9;i++) {
			
			if(sudoku[i][emptyY.get(depth)]==value) {
		//		System.out.println("duplicate column");
				return false;
			}
		}
		//3*3
		int startX = emptyX.get(depth)/3;
		int startY = emptyY.get(depth)/3;
	//	System.out.println("box check : ( "+startX+" , "+startY+" )");
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(sudoku[startX*3+i][startY*3+j]==value) {
		//			System.out.println(sudoku[startX*3+i][startY*3+j]+" : "+value);
		//			System.out.println("duplicate box");
					return false;
				}
			}
		}
		
	//	System.out.println(value + " in ("+emptyX.get(depth)+" , "+emptyY.get(depth)+" ) is possible");
		
		return true;
	}

}
