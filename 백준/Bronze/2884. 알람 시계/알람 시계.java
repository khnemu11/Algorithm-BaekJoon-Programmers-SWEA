import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalTime;
import java.util.StringTokenizer;

/*
 * 
 * 풀이 알고리즘
 * 
 * localtime 클래스를 사용하여 시간 표현
 * 
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int hour = Integer.valueOf(st.nextToken());
		int minute = Integer.valueOf(st.nextToken());

		LocalTime time = LocalTime.of(hour, minute);
		time = time.plusMinutes(-45);

		bw.write(time.getHour() + " " + time.getMinute());
		bw.newLine();
		bw.flush();
		bw.close();
	}

}