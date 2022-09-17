import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		LocalDate date = LocalDate.now();
			
		bw.write(String.valueOf(date.getYear()));
		bw.newLine();
		bw.write(String.valueOf(date.getMonthValue()));
		bw.newLine();
		bw.write(String.valueOf(date.getDayOfMonth()));
		bw.newLine();
	
		bw.flush();
		bw.close();
		
	}
}
