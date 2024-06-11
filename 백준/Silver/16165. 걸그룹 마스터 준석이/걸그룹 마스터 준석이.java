import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, List<String>> memberByTeamMap = new HashMap<>();
        Map<String, String> teamByMemberMap = new HashMap<>();

        for(int i=0;i<N;i++){
            String teamName = br.readLine();
            int count = Integer.parseInt(br.readLine());

            List<String> team = new ArrayList<>();

            for(int j=0;j<count;j++){
                String memberName = br.readLine();
                team.add(memberName);
                teamByMemberMap.put(memberName,teamName);
            }

            Collections.sort(team);
            memberByTeamMap.put(teamName,team);
        }

        for(int i=0;i<M;i++){
            String name = br.readLine();
            int type = Integer.parseInt(br.readLine());

            if(type == 0){
                List<String> team = memberByTeamMap.get(name);

                for(String member : team){
                    System.out.println(member);
                }
            }else if(type == 1){
                System.out.println(teamByMemberMap.get(name));
            }
        }
    }
}
