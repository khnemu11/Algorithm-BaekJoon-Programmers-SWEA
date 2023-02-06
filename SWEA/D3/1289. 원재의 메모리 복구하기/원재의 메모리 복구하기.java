import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
 
/*
 * 
   풀이 알고리즘
   현재 위치의 값이 1이라면 0으로 바꾸어야 하므로 0으로 변경
   직접 뒤집으면 최대 시간 복잡도가 N^2 이므로 개선 필요
   직접 뒤집지 말고 바꿔야 할 값 0,1을 번갈아 가며 찾으면 됨
    
   만약 현재 바꿔야할 값이이 1이면 해당 위치부터 끝까지의 값들중 1의 값이 ...->0->1로 변경되었다는 의미이므로 다음엔 0을 뒤집어야 된다
   만약 현재 바꿔야할 값이이 0이면 해당 위치부터 끝까지의 값들중 0의 값이 ...->1->0->1로 변경되었다는 의미이므로 다음엔 1을 뒤집어야 된다
    
   위의 방식을 이용해 O(N)으로 풀이 가능
*/
 
public class Solution {
    static int bits[];
    static int cnt;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.valueOf(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            bits = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
            cnt = 0;
            reverse(0, 1);
 
            StringBuilder result = new StringBuilder();
            result.append("#").append(test_case).append(" ").append(cnt).append("\n");
            bw.write(result.toString());
        }
        bw.flush();
    }
 
    public static void reverse(int curr, int target) {
        for (int i = curr; i < bits.length; i++) {
            if (bits[i] == target) {
                cnt++;
                reverse(i + 1, (~target) + 2);
                break;
            }
        }
    }
}