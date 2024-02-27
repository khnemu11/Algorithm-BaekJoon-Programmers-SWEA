import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitInput = br.readLine().split(" ");

        int N = Integer.parseInt(splitInput[0]);
        int K = Integer.parseInt(splitInput[1]);

        String input = br.readLine();
        boolean[] visited =new boolean[N+1];
        int count = 0;

        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == 'H'){
                continue;
            }

            for(int j=-K;j<=K;j++){
                if(i+j < 0 || i+j >= input.length()){
                    continue;
                }
                if(visited[i+j]){
                    continue;
                }
                if(input.charAt(i+j) == 'H'){
                    visited[i+j] = true;
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);
    }
}