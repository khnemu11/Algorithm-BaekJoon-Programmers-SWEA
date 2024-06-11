import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int UNKNOWN = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testcase=1;testcase<=T;testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            LocalDate date = LocalDate.of(y,m,m);
            LocalDate before = date.minusDays(m);

            System.out.println(String.format("%d %d %d",before.getYear(), before.getMonth().getValue(), before.getDayOfMonth()));
        }
    }
}
