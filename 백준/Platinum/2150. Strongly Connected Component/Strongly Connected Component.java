import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static ArrayList<ArrayList<Integer>> digraph; // 방향 그래프
    static ArrayList<ArrayList<Integer>> rdigraph; // 역방향 그래프
    static ArrayList<ArrayList<Integer>> res;
    static boolean[] visited;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.valueOf(st.nextToken()); // 정점의 개수
        int E = Integer.valueOf(st.nextToken()); // 간선의 개수

        digraph = new ArrayList<>();
        rdigraph = new ArrayList<>();
        res = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            digraph.add(new ArrayList<>());
            rdigraph.add(new ArrayList<>());
            res.add(new ArrayList<>());
        }

        // 단방향 인접리스트 구현 (방향 그래프, 역방향 그래프)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            digraph.get(A).add(B);
            rdigraph.get(B).add(A);
        }

        visited = new boolean[V + 1];
        stack = new Stack<>();

        // 방향 그래프에 대하여 dfs를 수행하고,
        // 탐색이 종료되는 점부터 스택에 push함.
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        Arrays.fill(visited, false);
        int groupNum = 0;

        // 역방향 그래프에 대하여 dfs룰 수행.
        // 이때, 
        while (!stack.isEmpty()) {
            int start = stack.pop();

            // 방문 체크된 것은 이미 start가
            // 하나의 SCC 그룹에 속해 있다는 뜻.
            if (visited[start]) {
                continue;
            }

            redfs(start, groupNum);
            groupNum++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(groupNum + "\n"); // SCC 그룹의 개수 출력.
        
        TreeMap<Integer, Integer> map = new TreeMap<>(); // key를 기준으로 오름차순 정렬.
        for (int i = 0; i < groupNum; i++) {
            Collections.sort(res.get(i)); // 각각의 SCC 그룹에 대해 오름차순 정렬.
            map.put(res.get(i).get(0), i); // key : SCC 그룹의 첫번째 항, value : 인덱스.
        }


        map.keySet().forEach(key -> {
            int value = map.get(key);

            for (int now : res.get(value)) {
                sb.append(now + " ");
            }
            sb.append("-1\n"); // 끝에 -1 붙여주는 것 잊지 말기.
        });

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs (int start) {
        visited[start] = true;

        for (int now : digraph.get(start)) {
            if (!visited[now]) {
                dfs(now);
            }
        }

        stack.push(start);
    }

    public static void redfs(int start, int groupNum) {
        visited[start] = true;
        res.get(groupNum).add(start);

        for (int now : rdigraph.get(start)) {
            if (!visited[now]) {
                redfs(now, groupNum);
            }
        }
    }
}
