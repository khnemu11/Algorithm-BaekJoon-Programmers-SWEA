import java.util.*;
import java.math.*;
import java.util.stream.*;

class Solution {
    List<Long> nums;
    String [] baseOps = {"+","-","*"};
    String [] inputOps;
    long max=0L;
    
    public long solution(String expression) {
        long answer = 0;
        nums = Arrays.stream(expression.split("[-\\*\\+]")).map(x->Long.valueOf(x)).collect(Collectors.toList());
        inputOps = Arrays.stream(expression.split("[0-9]")).filter(x ->!x.equals("")).toArray(String[]::new);
        
        selectPriority(new ArrayList<String>(),new boolean[3]);
        return max;
    }
    public void selectPriority(List<String> selected,boolean visited[]){
        if(selected.size()==3){
            // System.out.println(selected);
            List<Long>expression = copyList(nums);
            Queue<String>ops = copyArraytToQueue(inputOps);
            
            for(String currOp : selected){
                int idx=0;
                int loop = ops.size();
                while(loop-->0){
                    String targetOp = ops.poll();
                    if(!targetOp.equals(currOp)){
                        idx++;
                        ops.add(targetOp);
                    }else if(currOp.equals("+")){
                        expression.set(idx,expression.get(idx)+expression.get(idx+1));
                        expression.remove(idx+1);
                    }else if(currOp.equals("-")){
                        expression.set(idx,expression.get(idx)-expression.get(idx+1));
                        expression.remove(idx+1);
                    }else if(currOp.equals("*")){
                        expression.set(idx,expression.get(idx)*expression.get(idx+1));
                        expression.remove(idx+1);
                    }
                }
                // System.out.println(expression);
                // System.out.println(ops);
            }
            
            
            max=Math.max(max,Math.abs(expression.get(0)));
            return;
        }
        for(int i=0;i<baseOps.length;i++){
            if(visited[i]){
                continue;
            }
            visited[i]=true;
            selected.add(baseOps[i]);
            
            selectPriority(selected,visited);
            
            selected.remove(selected.size()-1);
            visited[i]=false;
        }
    }

    public List<Long> copyList (List<Long> list){
        List<Long> temp = new LinkedList<>();
        
        for(Long val : list){
            temp.add(val);
        }
        
        return temp;
    }
    public Queue<String> copyArraytToQueue (String[] arr){
        Queue<String> temp = new LinkedList<>();
        
        for(String val : arr){
            temp.add(val);
        }
        
        return temp;
    }
}