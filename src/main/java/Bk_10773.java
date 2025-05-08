import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Bk_10773 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int k = Integer.parseInt(input);
        Stack<Integer> stk = new Stack<>();
        for(int i =0; i<k ; i++){
            String input1 = br.readLine();
            int num = Integer.parseInt(input1);
            // 문제에서 정수가 "0"일 경우 지울 수 있는 수가 있음을 보장하기 때문에 0 인지만 확인해준다.
            if (num == 0){
                stk.pop();
                continue;
            }
            stk.push(num);
        }
        int result = 0;
        // 스택에 남은 모든 수를 더해준 후 출력
        while (!stk.empty()){
            result += stk.pop();
        }
        System.out.println(result);
    }
}
