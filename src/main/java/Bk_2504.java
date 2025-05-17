import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bk_2504 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Character> stk = new Stack<>();
        int ans = 1;
        int result = 0;
        // input으로  받은 괄호 문자열 반복문 수행
        // 현대 인덱스 이전의 인덱스의 문자 값을 save 변수에 저장
        char save = ' ';
        for(char ch: input.toCharArray()){
            // 여는 괄호 일 경우
            if(ch == '(' || ch == '['){
                stk.push(ch);
                // ans 를 통해 곱해야 되는 값을 누적해서 계산
                if (ch == '('){
                    ans *= 2;
                }
                else if (ch == '['){
                    ans *= 3;
                }
            }
            else{
                // 닫는 괄호 일 경우
                if (ch == ')'){
                    // 올바른 괄호열이 아닐 경우 result를 0으로 초기화
                    if (stk.isEmpty() || stk.peek() != '('){
                        result = 0;
                        break;
                    }
                    // result에 ans를 더하는 경우는 처음 주어진 괄호 문자열에서 '()' 또는 '[]'일 경우
                    else if (save == '('){
                        result += ans;
                    }
                    // ans 갱신
                    ans = ans / 2;
                    stk.pop();
                }
                if (ch == ']'){
                    if (stk.isEmpty() || stk.peek() != '['){
                        result = 0;
                        break;
                    }
                    else if (save == '['){
                        result += ans;
                    }
                    ans = ans / 3;
                    stk.pop();
                }
            }
            // save에 현재 괄호를 저장하고, 다음 괄호와 save 값이 쌍을 이룰 경우 result에 ans 값을 더함
            save = ch;
        }
        // stk에 값이 남아 있을 경우 올바르지 않은 괄호이므로 0 출력
        if (!stk.isEmpty()){
            System.out.println(0);
        }
        else{
            System.out.println(result);
        }
    }
}
