import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        String blanket = br.readLine();
        int value = 0;
        //올바른 괄호인지 확인

        if(!checkBlanket(blanket)){
           System.out.println(0);
        }else{
            int val = getBlanketValue(blanket,0,blanket.length()-1,1);
            System.out.println(val);
        }
    }
    public static boolean checkBlanket(String blanket){
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<blanket.length();i++){
            if(blanket.charAt(i) == '(' || blanket.charAt(i) == '['){
                stack.add(blanket.charAt(i));
            }else if(stack.isEmpty()){
                return false;
            }else if(stack.peek() == '[' && blanket.charAt(i) == ']'){
                stack.pop();
            }else if(stack.peek() == '(' && blanket.charAt(i) == ')'){
                stack.pop();
            }else{
                return false;
            }
        }
        return true;
    }
    public static int getBlanketValue(String blanket,int l, int r,int val){
        if(r < l){
//            System.out.println(l+"~"+r+" val : 1");
            return val;
        }

        int idx = l;
        int start = idx;
        Stack<Integer> stack = new Stack<>();
        List<Range> rangeList = new ArrayList<>();

        while(idx <=r){
            if(blanket.charAt(idx) == '('
                    || blanket.charAt(idx) == '['){
                stack.add(idx);
            }else{
                start = stack.pop();

                if(stack.isEmpty()){
                    rangeList.add(new Range(start, idx));
                }
            }
            idx++;
        }

        int sum = 0;

        for(Range range : rangeList){
            sum += getBlanketValue(blanket,range.start+1,range.end-1,blanket.charAt(range.start) == '(' ? 2 : 3);
        }

        return sum * val;
    }
}

class Range{
    int start;
    int end;

    public Range(int start, int end){
        this.start = start;
        this.end = end;
    }

    public String toString(){
        return start+" ~ "+end;
    }
}