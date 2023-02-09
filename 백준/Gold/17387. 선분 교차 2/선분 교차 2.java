import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*
    풀이 알고리즘
    세 점의 방향을 알려주는 ccw 알고리즘 학습 필요
    x1 x2 x3 x1 
    y1 y2 y3 y1
    x1*y2 + x2*y3 + x3+y1 - (x2*y1+x3*y2+x1*y3) < 0 -> 반시계방향
    x1*y2 + x2*y3 + x3+y1 - (x2*y1+x3*y2+x1*y3) == 0 -> 일직선
    x1*y2 + x2*y3 + x3+y1 - (x2*y1+x3*y2+x1*y3) > 0 -> 시계방향
    
    서로 같은 방향이면 선분이 교차하고 아니면 선분이 교차하지 않는다
    이때 서로 일직선이면 위치를 파악해서 겹치는 선인지 아닌지 판단 필요
*/
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer L1Tokens = new StringTokenizer(br.readLine());
		StringTokenizer L2Tokens = new StringTokenizer(br.readLine());

		Coordinate[] coords = new Coordinate[4];

		coords[0] = new Coordinate(Long.valueOf(L1Tokens.nextToken()), Long.valueOf(L1Tokens.nextToken()));
		coords[1] = new Coordinate(Long.valueOf(L1Tokens.nextToken()), Long.valueOf(L1Tokens.nextToken()));
		coords[2] = new Coordinate(Long.valueOf(L2Tokens.nextToken()), Long.valueOf(L2Tokens.nextToken()));
		coords[3] = new Coordinate(Long.valueOf(L2Tokens.nextToken()), Long.valueOf(L2Tokens.nextToken()));

		long l1 = ccw(coords[0], coords[1], coords[2]) * ccw(coords[0], coords[1], coords[3]);
		long l2 = ccw(coords[2], coords[3], coords[0]) * ccw(coords[2], coords[3], coords[1]);

		if (l1 == 0 && l2 == 0) {
			if (Math.min(coords[0].x, coords[1].x) <= Math.max(coords[2].x, coords[3].x)
					&& Math.min(coords[2].x, coords[3].x) <= Math.max(coords[0].x, coords[1].x)
					&& Math.max(coords[0].y, coords[1].y) >= Math.min(coords[2].y, coords[3].y)
					&& Math.min(coords[0].y, coords[1].y) <= Math.max(coords[2].y, coords[3].y)) {
				bw.write("1\n");
			} else {
				bw.write("0\n");
			}
		} else if (l1 <= 0 && l2 <= 0) {
			bw.write("1\n");
		} else {
			bw.write("0\n");
		}

		bw.flush();
	}

	public static long ccw(Coordinate a, Coordinate b, Coordinate c) {
		long rightDownSum = a.x * b.y + b.x * c.y + c.x * a.y;
		long rightUpSum = a.y * b.x + b.y * c.x + c.y * a.x;

		if (rightDownSum - rightUpSum < 0) {
			return -1;
		} else if (rightDownSum - rightUpSum > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}

class Coordinate {
	long x; // x
	long y; // y

	public Coordinate(long x, long y) {
		this.x = x;
		this.y = y;
	}
}