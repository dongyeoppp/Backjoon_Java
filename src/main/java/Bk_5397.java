import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Bk_5397 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = Integer.parseInt(input);
        for(int i =0; i<n ; i++){
            String input1 = br.readLine();
            String result = check(input1);
            System.out.println(result);
        }
    }
    // stack을 사용하여 풀이
    public static String check(String answer){
        Stack<Character> stk1 = new Stack<>();
        Stack<Character> stk2 = new Stack<>();

       for(int i=0; i<answer.length(); i++){
           char ans =answer.charAt(i);
           // char 은 '' , String 은 ""
           if (ans == '<'){
               if (!stk1.isEmpty()) {
                   stk2.add(stk1.pop());
               }
           }
           else if (ans == '>'){
               if (!stk2.isEmpty()){
                   stk1.add(stk2.pop());
               }
           }
           else if (ans == '-'){
               if(!stk1.isEmpty()){
                   stk1.pop();
               }
           }
           // 알파벳이나 숫자는 stk1에 넣기
           else{
               stk1.add(ans);
           }
       }
       // 반복문 이후에 stk2에 남아있는 문자가 있을 수 있으므로 stk1으로 옮겨준다.
       while (!stk2.isEmpty()){
           stk1.add(stk2.pop());
       }
       StringBuilder sb = new StringBuilder();
       for (char stk : stk1){
           sb.append(stk);
       }

       return sb.toString();
    }
}
