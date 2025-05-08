import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Bk_1874 {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = Integer.parseInt(input);
        int[] arr = new int[n];
        // 스택 활용
        Stack<Integer> stk = new Stack<>();
        // 문제에서 주어진 수열을 arr에 저장
        for(int i = 0 ; i < n ; i++){
            String input1 = br.readLine();
            arr[i] = Integer.parseInt(input1);
        }
        int start = 0;
        for(int i = 1 ; i <= n ; i++){
            if (stk.isEmpty() || arr[start] >= i){
                stk.push(i);
                sb.append("+\n");
            }
            // start(arr의 인덱스 값)을 갱신해주며 주어진 수열에 맞게 pop
            while (!stk.isEmpty() && arr[start] == stk.peek()){
                stk.pop();
                sb.append("-\n");
                start++;
            }
        }
        // 반복문이 종료 된 이후에도 스택에 값이 남아있지 않다면 가능한 수열
        if (stk.isEmpty()){
            System.out.println(sb);
        }
        // 불가능한 수열일 경우
        else{
            System.out.println("NO");
        }

    }
}
