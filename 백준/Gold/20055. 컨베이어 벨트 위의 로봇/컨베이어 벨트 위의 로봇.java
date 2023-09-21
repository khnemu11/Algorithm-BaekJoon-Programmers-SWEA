import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //내리는 위치
        int K = Integer.parseInt(st.nextToken()); //내구도가 0인 개수

        st = new StringTokenizer(br.readLine());
        List<Belt> container = new ArrayList<>();
        while(st.hasMoreTokens()){
            container.add(new Belt(Integer.valueOf(st.nextToken()),false));
        }

        int zeroCnt = 0; //0의 개수
        int round = 0;
        while(zeroCnt < K){
            //라운드 진행
            round++;
            //오른쪽으로 회전
            Belt last = container.get(container.size()-1);
            container.remove(container.size()-1);
            container.add(0,last);

            //N번째에 로봇이 가면 없애기
            if(container.get(N-1).robot){
                container.get(N-1).robot=false;
            }

            //로봇 이동(해당 위치가 0이 아니면)

            for(int i=N-1;i>=0;i--){
                //로봇이 없으면 패스
                if(!container.get(i).robot){
                    continue;
                }
                //다음 위치에 로봇이 있으면 패스
                if(container.get(i+1).robot){
                    continue;
                }
                //다음 위치의 내구도가 0이면 패스
                if(container.get(i+1).durability == 0){
                    continue;
                }

                container.get(i).robot=false;
                container.get(i+1).robot=true;
                container.get(i+1).durability--;

                //내구도가 떨어지면 0인 개수 증가
                if(container.get(i+1).durability == 0){
                    zeroCnt++;
                }
                //N번째에 도달하면 로봇 없애기
                if(i+1 == N-1){
                    container.get(i+1).robot=false;
                }
            }

            //0번 위치에 로봇 올리기(해당위치가 0이 아니면)
            if(!container.get(0).robot && container.get(0).durability>0){
                container.get(0).robot=true;
                container.get(0).durability--;
                if(container.get(0).durability ==0){
                    zeroCnt++;
                }
            }
        }
        System.out.println(round);
    }
}
class Belt{
    int durability;
    boolean robot;

    public Belt(int durability, boolean robot){
        this.durability = durability;
        this.robot = robot;
    }

    public String toString(){
        return durability+" robot : "+robot;
    }
}