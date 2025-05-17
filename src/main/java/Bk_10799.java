import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Position{
    char x;
    int y;
    public Position(char x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Bk_10799 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        // 스택에는 괄호 값과 그 괄호의 인덱스 값을 묶어서 넣어준다.
        Stack<Position> stk = new Stack<>();
        int result = 0;
        for(int i = 0; i < input.length() ; i++){
            char answer = input.charAt(i);
            // '(' 스택에 푸시
            if ( answer == '(') {
                stk.push(new Position(answer,i));
            }
            else{
                // ')'이 나올 경우는 무조건 스택의 맨 위의 값은 '('이므로 인덱스 값을 확인한 이후 pop 해준다.
                int ans2 = stk.peek().y;
                stk.pop();
                // 레이저일 경우
                if (i-ans2 == 1){
                    // 스택에 남아있는 괄호의 객수 만큼 더해준다. 그 만큼 쇠 막대기의 조강이 나오게 됨
                    result+= stk.size();
                }
                else {
                    // 쇠막대기의 길이가 끝날때 꼬투리 조각을 더해준다.
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
