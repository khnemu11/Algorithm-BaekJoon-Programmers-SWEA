import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
	public static void main(String []args) throws ParseException, IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String currentTime = br.readLine();
		int cookingTime = Integer.valueOf(br.readLine());
		
		SimpleDateFormat format = new SimpleDateFormat("HH mm");
		Date date = format.parse(currentTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, cookingTime);
		
		System.out.println(cal.getTime().getHours()+ " "+cal.getTime().getMinutes());
	}
}
