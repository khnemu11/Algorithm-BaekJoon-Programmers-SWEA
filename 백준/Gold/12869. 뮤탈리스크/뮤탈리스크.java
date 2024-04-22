import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        SCVGroup scvGroup = new SCVGroup();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int health = Integer.parseInt(st.nextToken());
            scvGroup.scv[i] = health;
        }

        int count = getMinCount(scvGroup);

        System.out.println(count);
    }

    public static int getMinCount(SCVGroup startGroup){
        PriorityQueue<SCVGroup> pq = new PriorityQueue<>((a,b)->(a.count - b.count));
        pq.add(startGroup);

        int minCount = Integer.MAX_VALUE;
        boolean[][][]visited = new boolean[61][61][61];

        while(!pq.isEmpty()){
            SCVGroup curr = pq.poll();

            visited[curr.scv[0]][curr.scv[1]][curr.scv[2]] = true;

            if(curr.scv[0] == 0 && curr.scv[1] == 0 && curr.scv[2] == 0){
                minCount = curr.count;
                break;
            }

            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i == j){
                        continue;
                    }
                    for(int k=0;k<3;k++){
                        if(i == k || j == k){
                            continue;
                        }

                        int[] nextSCV = new int[3];

                        nextSCV[i] =  curr.scv[i] - 9 < 0 ? 0 : curr.scv[i] - 9;
                        nextSCV[j] =  curr.scv[j] - 3 < 0 ? 0 : curr.scv[j] - 3;
                        nextSCV[k] =  curr.scv[k] - 1 < 0 ? 0 : curr.scv[k] - 1;

                        if(visited[nextSCV[0]][nextSCV[1]][nextSCV[2]]){
                            continue;
                        }

                        visited[nextSCV[0]][nextSCV[1]][nextSCV[2]] = true;

                        SCVGroup next = new SCVGroup(nextSCV,curr.count+1);
                        pq.add(next);
                    }
                }
            }
        }

        return minCount;
    }
}

class SCVGroup{
    int[] scv;
    int count;

    public SCVGroup(){
        scv = new int[3];
    }

    public SCVGroup(int[] scv, int count){
        this.scv = scv;
        this.count = count;
    }

}