import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
/*
    풀이 과정
    마름모의 배열 값을 더하면 되는 문제이므로 마름모를 절반을 기준으로 반으로 잘라 위, 아래의 넓이를 합하여 최종 넓이를 구한다.
*/
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int T = Integer.valueOf(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = Integer.valueOf(br.readLine());
 
            int arr[][] = new int[size][size];
 
            for (int i = 0; i < size; i++) {
                arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
            }
 
            int midIdx = size / 2;
            int sum = 0;
 
            for (int row = 0; row <= midIdx; row++) {
                for (int col = midIdx - row; col <= midIdx + row; col++) {
                    sum += arr[row][col];
                }
            }
 
            for (int row = midIdx + 1; row < size; row++) {
                for (int col = midIdx - (size - 1 - row); col <= midIdx + (size - 1 - row); col++) {
                    sum += arr[row][col];
                }
            }
 
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ").append(sum);
 
            bw.write(sb.toString() + "\n");
        }
 
        bw.flush();
    }
 
}