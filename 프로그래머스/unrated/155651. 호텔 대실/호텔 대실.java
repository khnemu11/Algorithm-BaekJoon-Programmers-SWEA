/*
    시계열 : 그리디
    끝나는 시간->일찍 시작하는 순서로 정렬하여 정렬
    예약이 끝나는 시간보다 다음 예약 시작 시간이 늦다 -> 현재 방으로 가능
    예약이 끝나는 시간보다 다음 예약 시작 시간이 빠르다 -> 새로운 방 개설
    -> arraylist로 관리 및 탐색
    
    최종 arraylist의 크기 반환
*/
import java.util.*;
import java.time.*;
import java.time.format.*;

class Solution {
    public int solution(String[][] book_time) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        
        PriorityQueue<Reservation> pq = new PriorityQueue<>();
        
        for(int i=0;i<book_time.length;i++){
            LocalTime start = LocalTime.parse(book_time[i][0],format);
            LocalTime end = LocalTime.parse(book_time[i][1],format);
            pq.add(new Reservation(start,end));
        }
        ArrayList<Reservation> rooms= new ArrayList<>();
        
        while(!pq.isEmpty()){
            Reservation reservation = pq.poll();
            boolean canJoin = false;
            for(int i=0;i<rooms.size();i++){
                //23시 50분 부터는 청소시간을 포함하면 다음날 00시 부터 예약이 가능하므로 제외
                if(rooms.get(i).end.isAfter(LocalTime.of(23,49))){
                    continue;
                }
                if(rooms.get(i).end.plusMinutes(9).isBefore(reservation.start)){
                    rooms.set(i,reservation);
                    canJoin=true;
                    break;
                }
            }
            
            if(!canJoin){
                rooms.add(reservation);
            }
            //방들을 끝나는 시간이 늦게 끝나는 시간으로 재정렬
            //늦게 끝나는 곳에 예약이 가능하면 일찍 끝나는곳도 가능하기 때문에 빈틈없이 예약 가능
            Collections.sort(rooms,new Comparator<Reservation>(){
                @Override
                public int compare(Reservation o1,Reservation o2){
                    if(o1.end.isAfter(o2.end)){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            });
        }   
        return rooms.size();
    }
}

class Reservation implements Comparable<Reservation>{
    LocalTime start;
    LocalTime end;
    
    public Reservation(LocalTime start, LocalTime end){
        this.start =start;
        this.end =end;
    }
    
    public int compareTo(Reservation o){
        if(this.start.isBefore(o.start)){
            return -1;
        }else{
            return 1;
        }
    }

    
    public String toString(){
        return start +" -> "+end;
    }
}
