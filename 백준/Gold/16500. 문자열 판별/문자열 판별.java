import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        boolean[]visited = new boolean[target.length()];

        int N = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet<>();

        for(int n=0;n<N;n++){
            set.add(br.readLine());
        }

        for(int start = target.length()-1;start >=0;start--){
            for(int end = start+1;end<target.length();end++){
                if(visited[end] && set.contains(target.substring(start,end))){
                    visited[start] = true;
                }
            }

            if(set.contains(target.substring(start))){
                visited[start] = true;
            }
        }
        System.out.println(visited[0] ?  1 : 0);
    }
}