import java.util.*;

class Solution {
    public int[] solution(String s) {
        String parsed = s.substring(2,s.length()-2); //양 끝 중괄호 2개 제거
        
        Set<Integer> nums = new LinkedHashSet<>();
        
        String [] tuples = parsed.split("\\},\\{"); // 튜플 간 },{를 기준으로 튜플 분리
        
        Arrays.sort(tuples,new Comparator<String>(){ // 길이 순으로 정렬
            @Override
            public int compare(String a, String b){
                    return a.length() - b.length();
            }
        });
         
        for(String tuple : tuples){ //각 튜플을 ,중심으로 분리 후 set에 저장
           String [] elements = tuple.split(",");
            for(String element : elements){
                nums.add(Integer.valueOf(element));
            }
        }
        
        int answer [] = new int[nums.size()]; //set을 배열로 변환
        int idx = 0;
        for(int num : nums){
            answer[idx] = num;
            idx++;
        }
            
        return answer;
    }
}