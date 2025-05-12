import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 스택에 담을 두 변수를 위한 객체 생성 (x = 탑의 높이, y = 탑의 위치)
class Position{
    int x;
    int y;
    // 생성자 (메서드 이름이 클래스와 동일 함)
    public Position(int x, int y){
        // this를 통해 매개변수와 구분
        this.x = x;
        this.y = y;
    }
}
public class Bk_2493 {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        Stack<Position> stk = new Stack<>();
        // 탑의 위치를 저장하는 리스트
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int answer = Integer.parseInt(arr[i]);
            // answer 보다 작은 탑은 신호를 수신할 수 없으므로 스택에서 뺀다.
            while(!stk.isEmpty() && stk.peek().x < answer){
                stk.pop();
            }
            // 신호를 수신할 수 있는 탑이 없는 경우
            if (stk.isEmpty()){
                result.add(0);
            }
            // 자신보다 크고 가장 가까운 탑의 위치를 반영
            else{
                result.add(stk.peek().y);
            }
            stk.push(new Position(answer,i+1));
        }
        for(int ans: result){
            sb.append(ans).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
