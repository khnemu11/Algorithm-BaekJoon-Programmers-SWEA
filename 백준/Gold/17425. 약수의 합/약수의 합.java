import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        int MAX = 1_000_000;
        long[] g = new long[MAX+1];
        long[] f = new long[MAX+1];

        for(int i=1;i<f.length;i++){
            for(int j=1; i*j <g.length; j++){
                f[i*j] += i;
            }
        }

        for(int i=1;i<g.length;i++){
            g[i] = g[i-1]+f[i];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            int N = Integer.parseInt(br.readLine());

            bw.append(String.valueOf(g[N]));
            bw.newLine();
        }

        bw.flush();
    }
}
