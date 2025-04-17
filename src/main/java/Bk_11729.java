import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_11729 {
    // 그냥 바로바로 출력하면 시간초과 발생하여 string builder에 출력값을 모두 저장하여 한번에 출력!!
    // final로 선언 함으로 다른 객체로 바뀌지 않음을 보증
    private static final StringBuilder sb = new StringBuilder();;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = Integer.parseInt(input);
        // 하노이 탑 규칙에 의하여 원판 개수에 따른 식 세우기 : 원판이 n개 일 때 2**n-1 번 원판 이동
        sb.append((int) Math.pow(2,n)-1).append("\n");
        hanoi(1,2,3,n);
        System.out.println(sb);
    }
    public static void hanoi(int start, int mid, int end, int num){
        // 원판이 하나 남았을 경우 시작 지점에서 목표 지점으로 원판 이동
        if (num == 1){
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }
        // 시작 지점에서 n-1개 원판을 중간 지점으로 옮김
        hanoi(start,end,mid,num-1);

        // n-1개의 원판을 옮긴 후 남은 원판을 시작지점에서 목표지점으로 옮김
        sb.append(start).append(" ").append(end).append("\n");

        // 중간 지점에 있는 원판 n-1개를 목표지점으로 옮김
        hanoi(mid,start,end,num-1);
    }
}
