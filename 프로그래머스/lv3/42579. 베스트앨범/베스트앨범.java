import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String,PriorityQueue> playsByGenre = new HashMap<>();
        Map<String,Integer> maxValByGenre = new HashMap<>();
        
        for(int i=0;i<genres.length;i++){
            Song song = new Song(genres[i],plays[i],i);
            // System.out.println("input "+song.seq+" : "+song.play);
            PriorityQueue<Song>pq = playsByGenre.getOrDefault(genres[i],new PriorityQueue<Song>());
            pq.add(song);
            playsByGenre.put(genres[i],pq);
            maxValByGenre.put(genres[i],maxValByGenre.getOrDefault(genres[i],0)+plays[i]) ;
        }
        
        PriorityQueue<Genre> genreQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String key: maxValByGenre.keySet()){
            genreQ.add(new Genre(key,maxValByGenre.get(key)));
        }
        
        ArrayList<Integer> list=new ArrayList<>();

        while(!genreQ.isEmpty()){
            Genre genre = genreQ.poll();
            // System.out.println(genre.name);
            PriorityQueue<Song> songQ = playsByGenre.get(genre.name);
            
            int cnt=0;
            
            while(!songQ.isEmpty() && cnt<2){
                Song song = songQ.poll();
                // System.out.println(song.seq+" : "+song.play);
                list.add(song.seq);
                cnt++;
            }
        }
        int answer [] = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}

class Song implements Comparable<Song>{
    String genre;
    int play;
    int seq;
    
    public Song(String genre,int play, int seq){
        this.genre = genre;
        this.play = play;
        this.seq = seq;
    }
    
    @Override
    public int compareTo(Song o){
        if(this.play == o.play){
            return this.seq - o.seq;
        }
        return o.play - this.play;
    }
}
class Genre implements Comparable<Genre>{
    String name;
    int play;
    
    public Genre(String name, int play){
        this.play = play;
        this.name = name;
    }
    
    @Override
    public int compareTo(Genre o){
        return this.play - o.play;
    }
}