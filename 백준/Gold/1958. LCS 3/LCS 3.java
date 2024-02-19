import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();
        String c = br.readLine();

        int[][][] dpABC = new int[a.length()+1][b.length()+1][c.length()+1];

        for(int i=0;i<a.length();i++){
            for(int j=0;j<b.length();j++){
                for(int k=0;k<c.length();k++){
                    if(a.charAt(i) == b.charAt(j) && c.charAt(k) == b.charAt(j)){
                        dpABC[i+1][j+1][k+1] = dpABC[i][j][k]+1;
                    }else{
                        dpABC[i+1][j+1][k+1] = Math.max(dpABC[i][j+1][k+1],Math.max(dpABC[i+1][j][k+1],dpABC[i+1][j+1][k]));
                    }
                }
            }
        }

        System.out.println(dpABC[a.length()][b.length()][c.length()]);
    }
}