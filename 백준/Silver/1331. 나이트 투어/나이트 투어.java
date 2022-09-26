import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		boolean visited[][] = new boolean[6][6];
		boolean valid = true;
		String square = br.readLine();
		int startRow = square.charAt(0) - 'A';
		int startCol = square.charAt(1) - '1';
		visited[startRow][startCol]=true;
		int currRow = startRow;
		int currCol = startCol;

		for (int i = 0; i < 35; i++) {
			square = br.readLine();
			int nextRow = square.charAt(0) - 'A';
			int nextCol = square.charAt(1) - '1';
//			System.out.println(square);
//			System.out.println("curr : " + currRow + " " + currCol);
//			System.out.println("next : " + nextRow + " " + nextCol);
//			System.out.println();
//			

			if (!visited[nextRow][nextCol] && ((Math.abs(nextRow - currRow) == 1 && Math.abs(nextCol - currCol) == 2)
					|| (Math.abs(nextRow - currRow) == 2 && Math.abs(nextCol - currCol) == 1))) {
				visited[nextRow][nextCol] = true;
			} else {
				valid = false;
				break;
			}
			currRow = nextRow;
			currCol = nextCol;
		}
//		System.out.println("curr : " + currRow + " " + currCol);
//		System.out.println("next : " + startRow + " " + startCol);
		if (Math.abs(startRow - currRow) == 1 && Math.abs(startCol - currCol) == 2
				|| Math.abs(startRow - currRow) == 2 && Math.abs(startCol - currCol) == 1) {
		} else {
			valid = false;
		}
		
		if(valid) {
			bw.write("Valid");	
		}
		else {
			bw.write("Invalid");	
		}

		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
