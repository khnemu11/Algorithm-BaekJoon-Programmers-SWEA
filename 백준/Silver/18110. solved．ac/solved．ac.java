import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(br.readLine());
        int[] comments= new int[n];

        for(int i=0;i<n;i++){
            int comment = Integer.parseInt(br.readLine());
            comments[i] = comment;
        }

        Arrays.sort(comments);
        int deleteAverage = (int)Math.round((double)n*0.15);
        double sum = 0;

        for(int i=deleteAverage;i<comments.length - deleteAverage;i++){
            sum += comments[i];
        }

        int average = (int)Math.round(sum / (n - deleteAverage*2));

        System.out.println(average);
    }
}