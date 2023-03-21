import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String,Car> carRecord = new TreeMap<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        
        int baseTime = fees[0];
        int baseFee = fees[1];
        int extraTime = fees[2];
        int extraFee = fees[3];
        
        for(String record : records){
            String recordParsed[] = record.split(" ");
            int fee=0;
            LocalTime time = LocalTime.parse(recordParsed[0],format);
            String carNo = recordParsed[1];
            
            Car car = new Car(carNo,time);
            
            if(recordParsed[2].equals("IN")){
                car.isParking = true;
                if(carRecord.get(carNo)==null){
                    carRecord.put(carNo,car);
                }else{
                    car.parkingTimeSum = carRecord.get(carNo).parkingTimeSum;
                    carRecord.put(carNo,car);
                }
            }else{
                car.isParking = false;
                int differ = (int)ChronoUnit.MINUTES.between(carRecord.get(carNo).parkingTime,time);
                car.parkingTimeSum = carRecord.get(carNo).parkingTimeSum + differ;
                carRecord.put(carNo,car);
            }
        }
        
        Set<String> keySet = carRecord.keySet();
        int[] answer = new int[keySet.size()];
        LocalTime end = LocalTime.of(23,59);
        int idx=0;
        for(String key : keySet){
            Car car = carRecord.get(key);
            int fee=0;
           
            if(car.isParking){
                int differ = (int)ChronoUnit.MINUTES.between(car.parkingTime,end);
                car.parkingTimeSum+=differ;
            }
            
            if(car.parkingTimeSum<=baseTime){
                fee = baseFee;
            }else{
                fee = (int)Math.ceil(((double)car.parkingTimeSum-baseTime)/extraTime)*extraFee + baseFee;
            }
            answer[idx]=fee;
            idx++;
        }
        return answer;
    }
}
class Car implements Comparable<Car>{
    String seq;
    LocalTime parkingTime;
    boolean isParking;
    int parkingTimeSum;
    
    public Car(String seq, LocalTime parkingTime){
        isParking=true;
        this.seq =seq;
        this.parkingTime = parkingTime;
        parkingTimeSum=0;
    }
    
    public String toString(){
        return seq+" : "+parkingTimeSum;
    }
    
    public int compareTo(Car o){
        return this.seq.compareTo(o.seq);
    }
}