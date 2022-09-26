import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		LocalDate startTime = LocalDate.of(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
st = new StringTokenizer(br.readLine());
		
		LocalDate endTime = LocalDate.of(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		
		String dday = "";
		
		if(ChronoUnit.YEARS.between(startTime, endTime)>=1000) {
			dday = "gg";
		}
		
		else {
			dday="D-"+ChronoUnit.DAYS.between(startTime, endTime);
		}
		
		bw.write(dday);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
