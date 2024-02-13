import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        char[][] matrixA = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        char[][] matrixB = new char[N][M];


        for(int i=0;i<N;i++){
            matrixA[i] = br.readLine().toCharArray();
        }
        for(int i=0;i<N;i++){
            matrixB[i] = br.readLine().toCharArray();
        }

        int flipCount = getFlipCount(matrixA,matrixB);

        if(isSame(matrixA,matrixB)){
            System.out.println(flipCount);
        }else{
            System.out.println(-1);
        }
    }

    public static int getFlipCount(char[][] matrixA, char[][] matrixB) {
        int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};

        int count = 0;

        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[row].length; col++) {
                if (matrixA[row][col] == matrixB[row][col]) {
                    continue;
                }

                Coordinate center = new Coordinate(row + 1, col + 1);

                if (center.row+1 >= matrixA.length || center.col+1 >= matrixA[row].length) {
                    continue;
                }

                for (int i = 0; i < dx.length; i++) {
                    Coordinate next = new Coordinate(center.row + dx[i], center.col + dy[i]);

                    matrixA[next.row][next.col] = matrixA[next.row][next.col] == '1' ? '0' : '1';
                }

                count++;
            }
        }

        return count;
    }

    public static boolean isSame(char[][] a, char[][]b){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                if(a[i][j] != b[i][j]){
                    return false;
                }
            }
        }

        return true;
    }
}

class Coordinate{
    int row;
    int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
}