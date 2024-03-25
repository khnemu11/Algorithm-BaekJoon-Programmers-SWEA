import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        Set<String> nicknames = new HashSet<>();

        for(int n=0;n<N;n++){
            String command = br.readLine();
            if(command.equals("ENTER")){
                count += nicknames.size();
                nicknames = new HashSet<>();
            }else{
                nicknames.add(command);
            }
        }
        count += nicknames.size();

        System.out.println(count);
    }
}
