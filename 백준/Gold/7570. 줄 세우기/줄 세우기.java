import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
*  플이과정)
*  숫자가 연속되면서 증가하는 가장 긴 수열의 길이를 구하면 되는 문제
*  왜냐하면 연속되면서 증가하는 수열을 만들어야 하는데 이미 원하는 조건대로 정렬되어 있으므로 건들지 않아도 되기 때문이다.
*  그러므로 이미 조건을 만족하는 수열 중 가장 긴 수열을 선택하고 나머지 숫자만 움직이면 최소 움직임으로 연속되면서 증가하믄 수열을 만들 수 있다.
*
* */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] length = new int[N+1]; //num[i]를 마지막으로 하는 연속되면서 증가하는 수열의 길이
        int max = 0; //연속되면서 증가하는 수열중 가장 긴 수열의 길이

        //1~i까지 연속되면서 증가하는 수열의 길이 = 1~i-1까지 연속되면서 증가하는 수열의 길이 + 1
        //이때 1~1수열을 위해 length[0]도 필요하므로 length[N] 수열을 N+1개로 증가시킨다.

        for(int i=0;i<num.length;i++){
            length[num[i]] = length[num[i]-1]+1;
            max = Math.max(max,length[num[i]]);
        }

        //이미 정렬된 가장 긴 수열을 제외하고 나머지를 이동한다.
        System.out.println(N - max);
    }
}