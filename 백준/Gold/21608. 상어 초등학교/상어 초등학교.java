import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public final static int EMPTY = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] classRoom = new int[N][N];

        Map<Integer, Set<Integer>> likeStudentMap = new HashMap<>();

        for (int i = 0; i < N*N; i++) {
            Set<Integer> likeSet = new HashSet<>();
            String[] input = br.readLine().split(" ");

            int student = Integer.parseInt(input[0]);

            for (int j = 1; j < input.length; j++) {
                likeSet.add(Integer.parseInt(input[j]));
            }

            likeStudentMap.put(student, likeSet);

            Seat seat = getFavoriteSeat(likeSet,classRoom);

            classRoom[seat.row][seat.col] = student;
        }

        int staticfaction = getStaticfaction(likeStudentMap,classRoom);

        System.out.println(staticfaction);

    }
    public static int getStaticfaction(Map<Integer, Set<Integer>> likeStudentMap, int[][] classRoom){
        int statisfaction = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int count = 0;

        for (int row = 0; row < classRoom.length; row++) {
            for (int col = 0; col < classRoom[row].length; col++) {
                int student = classRoom[row][col];
                count = 0;

                for (int k = 0; k < dx.length; k++) {
                    Seat next = new Seat(row + dx[k], col + dy[k]);

                    if (next.row < 0 || next.col < 0 || next.row >= classRoom.length || next.col >= classRoom[row].length) {
                        continue;
                    }

                    if (likeStudentMap.get(student).contains(classRoom[next.row][next.col])) {
                        count++;
                    }
                }

                switch (count){
                    case 1:
                        statisfaction += 1;
                        break;
                    case 2:
                        statisfaction += 10;
                        break;
                    case 3:
                        statisfaction += 100;
                        break;
                    case 4:
                        statisfaction += 1000;
                        break;
                }
            }
        }

        return statisfaction;
    }
    public static Seat getFavoriteSeat(Set<Integer> likeSet, int[][] classRoom) {
        PriorityQueue<Seat> pq = new PriorityQueue<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int row = 0; row < classRoom.length; row++) {
            for (int col = 0; col < classRoom[row].length; col++) {
                if(classRoom[row][col] != EMPTY){
                    continue;
                }
                Seat seat = new Seat(row, col);

                for (int k = 0; k < dx.length; k++) {
                    Seat next = new Seat(row + dx[k], col + dy[k]);

                    if (next.row < 0 || next.col < 0 || next.row >= classRoom.length || next.col >= classRoom[row].length) {
                        continue;
                    }

                    if (classRoom[next.row][next.col] == EMPTY) {
                        seat.empty++;
                    } else if (likeSet.contains(classRoom[next.row][next.col])) {
                        seat.like++;
                    }
                }

                pq.add(seat);
            }
        }

        return pq.poll();
    }
}

class Seat implements Comparable<Seat>{
    int row;
    int col;
    int like;
    int empty;

    public Seat(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Seat o) {

        if(this.like == o.like){
            if(this.empty == o.empty){
                if(this.row == o.row){
                    return this.col = o.col;
                }
                return this.row - o.row;
            }
            return o.empty - this.empty;
        }
        return o.like - this.like;
    }
}