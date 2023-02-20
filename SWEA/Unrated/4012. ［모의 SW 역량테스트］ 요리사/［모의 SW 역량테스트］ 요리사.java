import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
    static int min;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.valueOf(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.valueOf(br.readLine());
            int synergy[][] = new int[N][N];
            boolean visited[] = new boolean[N];
            min = Integer.MAX_VALUE;
 
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.valueOf(st.nextToken());
                }
            }
 
            pick(visited, synergy, 0, 0);
 
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ").append(min).append("\n");
            bw.write(sb.toString());
        }
        bw.flush();
    }
 
    public static void pick(boolean visited[], int synergy[][], int count, int idx) {
        if (count == synergy.length / 2) {
            int selectedSum = 0;
            int unSelectedSum = 0;
 
            for (int i = 0; i < synergy.length; i++) {
                for (int j = i + 1; j < synergy[0].length; j++) {
                    if (visited[i] && visited[j]) {
                        selectedSum = selectedSum + synergy[i][j] + synergy[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        unSelectedSum = unSelectedSum + synergy[i][j] + synergy[j][i];
                    }
                }
            }
 
            min = Math.min(min, Math.abs(unSelectedSum - selectedSum));
        } else {
            for (int i = idx; i < synergy.length; i++) {
                if (visited[i]) {
                    continue;
                }
 
                visited[i] = true;
                pick(visited, synergy, count + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}