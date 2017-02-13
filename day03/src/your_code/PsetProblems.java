package your_code;

import ADTs.StackADT;
import java.util.Stack;

public class PsetProblems {

    public static int longestValidSubstring(String s) {
        Stack<Integer> stk = new Stack<Integer>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(!stk.isEmpty() && c==')' && s.charAt(stk.peek())=='(')
                stk.pop();
            else
                stk.push(i);
        }

        int end_valid = s.length();
        int start_valid = s.length();

        int longest = 0;

        for(int i=s.length()-1; i>=0; i--){
            if(!stk.isEmpty() && stk.peek() == i){
                stk.pop();
                end_valid = i;
            }
            else{
                start_valid = i;
            }
            if(end_valid-start_valid>longest)
            {
                longest = end_valid-start_valid;
            }
        }
        return longest;


    }

    /*
    Sort stack ascending order smallest on top only one addtl stack
    * */
    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {
        StackADT<Integer> helperStack = new MyStack();

        Integer current ;
        while(!s.isEmpty()){
            current = s.pop();

            while(!helperStack.isEmpty() && !(current <= helperStack.peek())){
                s.push(helperStack.pop());
            }

            helperStack.push(current);
        }
        return helperStack;

    }

}
