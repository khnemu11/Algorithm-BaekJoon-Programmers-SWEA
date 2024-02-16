import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.*;

public class Main {
    public static int N,M,K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        Queue<FireBall> q = new LinkedList<>();

        for(int i=0;i<M;i++){
            input = br.readLine().split(" ");
            FireBall fireBall = new FireBall(Integer.parseInt(input[0])-1,
                    Integer.parseInt(input[1])-1,
                    Integer.parseInt(input[2]),
                    Integer.parseInt(input[3]),
                    Integer.parseInt(input[4]));

            q.add(fireBall);
        }

        //이동
        for(int round=1;round<=K;round++){
//            System.out.println("========================");
//            System.out.println("round : "+round);
//            System.out.println("========================");
//            System.out.println(q);
            Map<Coordinate,List<FireBall>> fireBallMap = move(q);

            for(List<FireBall> fireBallList: fireBallMap.values()){
//                System.out.println("이동후 합쳐질 파이어볼");
//                System.out.println(fireBallList);

                if(fireBallList.size() == 1){
                    q.add(fireBallList.get(0));
                    continue;
                }

                int row = fireBallList.get(0).row;
                int col = fireBallList.get(0).col;
                int weightSum = fireBallList.get(0).weight;
                int speedSum = fireBallList.get(0).speed;
                boolean allOddOrEven = true;

                for(int i=1;i<fireBallList.size();i++){
                    weightSum+=fireBallList.get(i).weight;
                    speedSum+=fireBallList.get(i).speed;

                    if(fireBallList.get(i).direction %2 != fireBallList.get(i-1).direction %2){
                        allOddOrEven = false;
                    }
                }

                int weight = weightSum/5;
                int speed = speedSum/fireBallList.size();

                FireBall next = new FireBall(row,col,weight,speed,1);
//                System.out.println("next FireBall "+next);

                if(weight == 0){
                    continue;
                }else if(allOddOrEven){
                    for(int direction=0;direction<=6;direction+=2){
                        q.add(new FireBall(row,col,weight,speed,direction));
                    }
                }else{
                    for(int direction=1;direction<=7;direction+=2){
                        q.add(new FireBall(row,col,weight,speed,direction));
                    }
                }

            }
        }

        int sum = 0;

        for(FireBall fireBall : q){
            sum += fireBall.weight;
        }

        System.out.println(sum);
    }

    public static Map<Coordinate,List<FireBall>> move(Queue<FireBall> q){
        Map<Coordinate,List<FireBall>> fireBallMap = new HashMap<>();

        int[]dx = {-1,-1,0,1,1,1,0,-1};
        int[]dy = {0,1,1,1,0,-1,-1,-1};

        while(!q.isEmpty()){
            FireBall fireBall = q.poll();
//            System.out.print(fireBall+" -> ");
            fireBall.row = (fireBall.row + dx[fireBall.direction]*fireBall.speed+ N*fireBall.speed) % N;
            fireBall.col = (fireBall.col + dy[fireBall.direction]*fireBall.speed+ N*fireBall.speed) % N;
            Coordinate coordinate = new Coordinate(fireBall.row,fireBall.col);

//            System.out.println(fireBall);

            fireBallMap.computeIfAbsent(fireBall, k -> new ArrayList<>());
            fireBallMap.get(fireBall).add(fireBall);

        }

        return fireBallMap;
    }
}

class Coordinate{
    int row;
    int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

class FireBall extends Coordinate{
    int weight;
    int direction;
    int speed;

    public FireBall(int row, int col, int weight, int speed, int direction){
        super(row,col);
        this.direction = direction;
        this.speed = speed;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "FireBall{" +
                "weight=" + weight +
                ", direction=" + direction +
                ", speed=" + speed +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}