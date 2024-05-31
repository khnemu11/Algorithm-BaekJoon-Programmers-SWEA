import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long answer = 0L;
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);
        for (int i = 0; i < N; i++) {
            if (data[i] > 0) break;
            int start = i + 1;
            int end = N - 1;

            while (start < end) {
                int s = 1;
                int e = 1;
                int current = data[i] + data[start] + data[end];
                if (current == 0) {
                    if (data[start] == data[end]) {    // start == end일 경우
                        answer += comb(end - start + 1);
                        break;
                    }
                    // start의 다음 값이 같은 경우
                    while (start + 1 < end && data[start] == data[start + 1]) {
                        s++;
                        start++;
                    }
                    // end의 다음 값이 같은 경우
                    while (start < end - 1 && data[end] == data[end - 1]) {
                        e++;
                        end--;
                    }

                    answer += s * e;
                }

                if (current > 0)
                    end--;
                else start++;
            }
        }
        System.out.println(answer);
    }

    static int comb(int n) {
        return n * (n - 1) / 2;
    }
}