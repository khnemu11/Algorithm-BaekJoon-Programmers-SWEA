import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] solution(int N, int[] stages) {
        int [] answer = new int [N];
        List<Stage> myStages = new ArrayList<Stage>();
		
		Integer stagesInteger[] =Arrays.stream(stages).boxed().toArray(Integer[]::new);
		Arrays.sort(stagesInteger, Collections.reverseOrder());
		//System.out.println(Arrays.toString(stagesInteger));
		
		for(int i=1;i<=N;i++) {
			int total = 0;
			int fail = 0;
			double failProp=0;
			for(int j=0;j<stagesInteger.length;j++) {
				//System.out.println("i : "+i + " " + " j : "+j);
				if(stagesInteger[j] < i) {
				//	System.out.println("finish");
					break;
				}
				if(stagesInteger[j] == i) {
					total++;
					fail++;
				}
				if(stagesInteger[j]>i) {
					total++;
				}
			}
		//	System.out.println(total + " " + fail);
			if(total != 0) {
				failProp = (double)fail/total;	
			}
		//	System.out.println((double)failProp);
			myStages.add(new Stage(i,failProp));
		}
		
		
		Collections.sort(myStages);
		//System.out.println(myStages.toString());
		
		for(int i=0;i<myStages.size();i++) {
			answer[i] = myStages.get(i).getStep();
		}
	//	System.out.println(Arrays.toString(answer));
        
        return answer;
    }
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
	
	@Override
	public String toString() {
		return "Stage [step=" + step + ", failedProp=" + failedProp + "]";
	}
}
}