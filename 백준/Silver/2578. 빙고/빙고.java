import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] board = new int[5][5];
        boolean[][] check = new boolean[5][5];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<board.length;i++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<Integer> callList = new ArrayList<>();

        for(int i=0;i<5;i++){
            StringTokenizer st  = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++){
                callList.add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0;i<callList.size();i++){
            for(int r=0;r<board.length;r++){
                for(int c=0;c<board[r].length;c++){
                    if(board[r][c] == callList.get(i)){
                        check[r][c] = true;
                    }
                }
            }

            if(findBinGo(check)){
                System.out.println(i+1);
                break;
            }
        }
    }
    public static boolean findBinGo(boolean[][] check){
        int bingo = 0;

        //열확인
        for(int i=0;i<check.length;i++){
            int count = 0;
            for(int j=0;j<check[i].length;j++){
                if(check[i][j]){
                    count++;
                }
            }
            if(count == 5){
                bingo++;
            }
        }

        //행확인
        for(int i=0;i<check[0].length;i++){
            int count = 0;
            for(int j=0;j<check.length;j++){
                if(check[j][i]){
                    count++;
                }
            }
            if(count == 5){
                bingo++;
            }
        }
        int count = 0;
        //왼대각선확인
        for(int i=0;i<check.length;i++){
            if(check[i][i]){
                count++;
                if(count == 5){
                    bingo++;
                }
            }
        }

        count = 0;
        //오른대각확인
        for(int i=check.length-1;i>=0;i--){
            if(check[check.length-1-i][i]){
                count++;
                if(count == 5){
                    bingo++;
                }
            }
        }
        return bingo >= 3 ? true : false;
    }
}