import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<Integer,String> minuteStringMap = new HashMap<>();
        Map<Integer,String> hourStringMap = new HashMap<>();

        minuteStringMap.put(1,"one");
        minuteStringMap.put(2,"two");
        minuteStringMap.put(3,"three");
        minuteStringMap.put(4,"four");
        minuteStringMap.put(5,"five");
        minuteStringMap.put(6,"six");
        minuteStringMap.put(7,"seven");
        minuteStringMap.put(8,"eight");
        minuteStringMap.put(9,"nine");
        minuteStringMap.put(10,"ten");
        minuteStringMap.put(11,"eleven");
        minuteStringMap.put(12,"twelve");
        minuteStringMap.put(13,"thirteen");
        minuteStringMap.put(14,"fourteen");
        minuteStringMap.put(15,"quarter");
        minuteStringMap.put(16,"sixteen");
        minuteStringMap.put(17,"seventeen");
        minuteStringMap.put(18,"eighteen");
        minuteStringMap.put(19,"nineteen");
        minuteStringMap.put(20,"twenty");
        minuteStringMap.put(21,"twenty one");
        minuteStringMap.put(22,"twenty two");
        minuteStringMap.put(23,"twenty three");
        minuteStringMap.put(24,"twenty four");
        minuteStringMap.put(25,"twenty five");
        minuteStringMap.put(26,"twenty six");
        minuteStringMap.put(27,"twenty seven");
        minuteStringMap.put(28,"twenty eight");
        minuteStringMap.put(29,"twenty nine");
        minuteStringMap.put(30,"half");

        hourStringMap.put(1, "one");
        hourStringMap.put(2, "two");
        hourStringMap.put(3, "three");
        hourStringMap.put(4, "four");
        hourStringMap.put(5, "five");
        hourStringMap.put(6, "six");
        hourStringMap.put(7, "seven");
        hourStringMap.put(8, "eight");
        hourStringMap.put(9, "nine");
        hourStringMap.put(10, "ten");
        hourStringMap.put(11, "eleven");
        hourStringMap.put(12, "twelve");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int hour = Integer.parseInt(br.readLine());
        int minute = Integer.parseInt(br.readLine());

        if (minute == 0) {
            System.out.printf("%s o' clock%n", hourStringMap.get(hour));
        } else if (minute <= 30) {
            if (minute == 1) {
                System.out.printf("one minute past %s%n", hourStringMap.get(hour));
            } else if (minute == 15 || minute == 30) {
                System.out.printf("%s past %s%n", minuteStringMap.get(minute), hourStringMap.get(hour));
            } else {
                System.out.printf("%s minutes past %s%n", minuteStringMap.get(minute), hourStringMap.get(hour));
            }
        } else {
            int toHour = hour == 12 ? 1 : hour + 1;
            minute = 60 - minute;
            if (minute == 1) {
                System.out.printf("one minute to %s%n", hourStringMap.get(toHour));
            } else if (minute == 15) {
                System.out.printf("quarter to %s%n", hourStringMap.get(toHour));
            } else {
                System.out.printf("%s minutes to %s%n", minuteStringMap.get(minute), hourStringMap.get(toHour));
            }
        }
    }
}
