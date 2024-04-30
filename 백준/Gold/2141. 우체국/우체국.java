import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Village[] villeges = new Village[N];

        long total = 0;

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            villeges[i] = new Village(i+1,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

            total += villeges[i].count;
        }

        Arrays.sort(villeges,(a,b)->(a.location - b.location));

        long count = 0;
        long mid = (total+1)/2;

        for(int i=0;i<villeges.length;i++){
            count += villeges[i].count;

            if(count >= mid){
                System.out.println(villeges[i].location);
                break;
            }
        }
    }
}
class Village{
    int seq;
    int location;
    int count;

    public Village(int seq, int location, int count){
        this.seq = seq;
        this.location = location;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Villege{" +
                "seq=" + seq +
                ", location=" + location +
                ", count=" + count +
                '}';
    }
}
