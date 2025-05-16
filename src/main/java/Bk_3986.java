import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bk_3986 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i = 0; i<n; i++){
            String input = br.readLine();
            Stack<Character> stk = new Stack<>();
            for(char ch: input.toCharArray()){
                // 스택이 비어있을 경우
                if (stk.isEmpty()){
                    stk.push(ch);
                    continue;
                }
                // 스택의 맨 위의 값이 현재 넣으려고 하는 문자 값과 같을 경우
                if(stk.peek().equals(ch)){
                    stk.pop();
                    continue;
                }
                // 스택의 맨 위의 값이 현재 넣으려고 하는 문자 값과 다를 경우
                stk.push(ch);
            }
            // 반복문 종료 후 스택이 비어 있으면 좋은 단어
            if (stk.isEmpty()){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
