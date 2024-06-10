import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] teamHI = new int[N];
        int[] teamHICount = new int[100001];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            teamHI[i] = Integer.parseInt(st.nextToken());
            teamHICount[teamHI[i]]++;
        }

        int[] teamARC = new int[M];
        int[] teamARCCount = new int[100001];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<M;i++){
            teamARC[i] = Integer.parseInt(st.nextToken());
            teamARCCount[teamARC[i]]++;
        }

        Arrays.sort(teamHI);
        Arrays.sort(teamARC);

        long hiwin=0, draw=0;
        int arcIdx = 0;
        long hiWinCount = 0;

        for(int hiIdx = 0; hiIdx < N; hiIdx++){
            while(arcIdx < M){
                if(teamARC[arcIdx] >= teamHI[hiIdx]){
                    break;
                }
                arcIdx++;
                hiWinCount++;
            }
            hiwin+=hiWinCount;
            draw+=teamARCCount[teamHI[hiIdx]];
        }


        long arcWin = 0;
        long arcWinCount = 0;
        arcIdx=0;
        int hiIdx= 0;

        for(; arcIdx < M; arcIdx++){
            while(hiIdx < N){
                if(teamHI[hiIdx] >= teamARC[arcIdx]){
                    break;
                }
                hiIdx++;
                arcWinCount++;
            }
            arcWin+=arcWinCount;
        }

        System.out.println(String.format("%d %d %d",hiwin,arcWin,draw));
    }
}