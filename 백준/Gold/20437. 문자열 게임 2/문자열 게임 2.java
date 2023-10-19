import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testcase=1;testcase<=T;testcase++){
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            Map<Character, List<Integer>> countMap = new HashMap<>();

            for(int i=0;i<str.length();i++){
                Character key = str.charAt(i);
                List<Integer> indexList = countMap.get(key);

                if(countMap.get(key) == null){
                    indexList =new ArrayList<>();
                }

                indexList.add(i);
                countMap.put(key,indexList);
            }

            int thirdLength = Integer.MAX_VALUE;
            int fourthLength = 0;

            for(Character key : countMap.keySet()){
                List<Integer> indexList = countMap.get(key);

                if(indexList.size() < K){
                    continue;
                }

                for(int i=0;i+K-1<indexList.size();i++){
                    int start = indexList.get(i);
                    int end = indexList.get(i + K - 1);
                    int length = end - start + 1;

                    if(thirdLength > length){
                        thirdLength = length;
                    }
                    if(fourthLength < length){
                        fourthLength = length;
                    }
                }

            }

            if(thirdLength == Integer.MAX_VALUE){
                System.out.println(-1);
            }else{
                System.out.println(thirdLength+" "+fourthLength);
            }
        }
    }
}