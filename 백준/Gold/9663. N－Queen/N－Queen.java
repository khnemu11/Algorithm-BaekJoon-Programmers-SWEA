import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static boolean board[][];
	static int count;
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());
		
		board=new boolean [n][n];
		
		count = 0;
		
		findNQueen(0,n);
		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

	public static void findNQueen(int depth,int n){
		if(depth==n) {
			count++;
//			System.out.println("성공!");
//			System.out.println(Arrays.deepToString(board));
		}
		else {
			for(int i=0;i<n;i++) {
				boolean permit=true;
//				System.out.println(depth +" , "+i+" 확인 >>");
				for(int j=0;j<depth;j++) {
					//세로
					if(board[j][i]) {
//						System.out.println("세로 실패 : "+j +" "+i);
//						System.out.println(Arrays.deepToString(board));
						
						permit=false;
						break;
					}
					//가로
					else if(board[depth][j]) {
//						System.out.println("가로 실패 "+depth +" "+j);
//						System.out.println(Arrays.deepToString(board));
						permit=false;
						break;
					}
					//대각선 왼쪽 위
					else if(i-depth+j>=0 && board[j][i-depth+j]) {
//						System.out.println("왼쪽 실패 :"+j+" "+(i-depth+1));
//						System.out.println(Arrays.deepToString(board));
						permit=false;
						break;
					}
					//대각선 오른쪽 위
					else if(i+depth-j< n && board[j][i+depth-j]) {
//						System.out.println("오른쪽 실패 : "+j+" "+(i+depth+1));
//						System.out.println(Arrays.deepToString(board));
						permit=false;
						break;
					}
				}
				if(permit) {
					board[depth][i]=true;
					findNQueen(depth+1,n);
					board[depth][i]=false;
				}
			}
		}
	}
}