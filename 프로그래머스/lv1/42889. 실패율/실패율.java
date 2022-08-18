import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] solution(int N, int[] stages) {
        int [] answer = new int [N];
        List<Stage> myStages = new ArrayList<Stage>();
		//reverseOrder를 사용하기 위해 int -> Integer로 변경
		Integer stagesInteger[] =Arrays.stream(stages).boxed().toArray(Integer[]::new);
        //많이 깬사람 순으로 stages를 역정렬하여 많이 깬 사람도 못깼으면 적게 깬 사람도 못깼으므로 탐색을 위한 반복문 횟수 감소 가능
        // ex: 7스테이지를 도전 중인 사람을 탐색하는 경우 역정렬 되어있으므로 현재 도전중인 사람이 6스테이지를 도전중이면 이후 사람들은 무조건 7스테이지를 도전조차 못하는 사람이므로 이후 탐색을 넘어갈 수 있다.
		Arrays.sort(stagesInteger, Collections.reverseOrder());
		
        //1~N스테이지까지 실패율 계산
		for(int i=1;i<=N;i++) {
			int total = 0;
			int fail = 0;
			double failProp=0;
            //현재 도전중인 스테이지 탐색
			for(int j=0;j<stagesInteger.length;j++) {
                //현재 도전중인 스테이지보다 단계가 높은 스테이지인 경우 -> 나머지 플레이어는 도달하지 못함
				if(stagesInteger[j] < i) {
					break;
				}
                //현재 도전중인 스테이지와 단계가 같은경우 -> 도전중
				if(stagesInteger[j] == i) {
					total++;
					fail++;
				}
                //현재 도전중인 스테이지보다 단계가 큰경우 -> 클러어함
				if(stagesInteger[j]>i) {
					total++;
				}
			}
            //도전or 클리어한 사람이 1명이라도 있는 경우만 실패율 계산
			if(total != 0) {
                //둘다 int형이므로 소수계산을 위해 double로 형변환
				failProp = (double)fail/total;	
			}
            //계산한 실패율과 단계를 가지고 stage추가
			myStages.add(new Stage(i,failProp));
		}
        //실패율이 높으면서 단계가 낮은 순서로 정렬
		Collections.sort(myStages);
		
        //단계만 뽑아 int형 배열로 추출
		for(int i=0;i<myStages.size();i++) {
			answer[i] = myStages.get(i).getStep();
		}
        
        return answer;
    }
    //현재 단계와 실패율을 가지고 있는 객체 클래스
    class Stage implements Comparable<Stage>{
	int step;
	double failedProp;
	
	public Stage(int step, double failedProp) {
		this.step = step;
		this.failedProp=failedProp;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public double getFailedProp() {
		return failedProp;
	}

	public void setFailedProp(int failedProp) {
		this.failedProp = failedProp;
	}
	 //실패율이 높으면서 단계가 낮은 순서로 정렬
	@Override
	public int compareTo(Stage o) {
		if(this.failedProp >o.failedProp) {
			return -1;
		}
		else if(this.failedProp  == o.failedProp) {
			if(this.step < o.step) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else{
			return 1;
		}
	}
}
}