import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int month = Integer.valueOf(st.nextToken());
		int day = Integer.valueOf(st.nextToken());
		
		LocalDate date = LocalDate.of(2007, month, day);
		String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase();
		
		bw.write(dayOfWeek);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}