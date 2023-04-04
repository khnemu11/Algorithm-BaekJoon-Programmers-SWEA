import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
/*
 * 풀이 과정
 * 
 * 내 키를 구할 수 있다
 * -> 나보다 키가 작은 애와 큰 애의 합이 전체 학생 수와 같다.
 * a < b 를 a->b로 표현함
 * 자신보다 키가 작은 애의 합 = 그래프에서 자신과 연결되어 있는 노드의 합
 * 자신보다 키가 큰 애의 합 = 역그래프에서 자신과 연결되어 있는 노드의 합
 * 
 * 모든 노드와 노드와 연결되어 있는지 확인하는 알고리즘 => 플로이드 워셜
 * */
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int T =Integer.valueOf(br.readLine());
         
        for(int test_case=1;test_case<=T;test_case++){
        int N = Integer.valueOf(br.readLine());
        int E = Integer.valueOf(br.readLine());
 
        int graph[][] = new int[N + 1][N + 1];  //자신보다 작은 것을 표현할 정방향 그래프
        int inverseGraph[][] = new int[N + 1][N + 1];   //자신보다 큰 것을 표현할 역방향 그래프
 
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
 
            graph[from][to] = 1; // 연결되어 있다는 것을 1로 표현
            inverseGraph[to][from] = 1; // 연결되어 있다는 것을 1로 표현
        }
 
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    // 출발점과 끝점이 같은 경우 연결되어 있으므로 패스
                    if (graph[start][mid] == 1 && graph[mid][end] == 1) {
                        graph[start][end] = 1;
                    }
                    if (inverseGraph[start][mid] == 1 && inverseGraph[mid][end] == 1) {
                        inverseGraph[start][end] = 1;
                    }
                }
            }
        }
 
        int totalCnt = 0;
 
        for (int i = 1; i <= N; i++) {
            int biggerCnt = Arrays.stream(graph[i]).sum();
            int smallCnt = Arrays.stream(inverseGraph[i]).sum();
            if (biggerCnt + smallCnt + 1 == N) { // 자기 자신 + 자신보다 큰 것 + 자신보다 작은 것의 개수 == 총 개수와 같다 -> 크기를 알 수 있다
                totalCnt++;
            }
        }
        bw.write("#"+test_case+" "+totalCnt+"\n");
        bw.flush();
        }
    }
}