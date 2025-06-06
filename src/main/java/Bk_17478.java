import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_17478 {
    public static int n;
    public static StringBuilder sb = new StringBuilder();
    public static void recall(int cnt){
        // "_" 출력 횟수 cnt로 측정
        int count = cnt * 4;
        // 문자열 반복하기 위해 repeat() 사용
        sb.append("_".repeat(count)).append("\"재귀함수가 뭔가요?\"").append("\n");
        // 재귀 종료 조건
        if (cnt == n){
            sb.append("_".repeat(count)).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"").append("\n");
            sb.append("_".repeat(count)).append("라고 답변하였지.").append("\n");
            return;
        }
        sb.append("_".repeat(count)).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append("\n");
        sb.append("_".repeat(count)).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append("\n");
        sb.append("_".repeat(count)).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append("\n");
        recall(cnt+1);
        sb.append("_".repeat(count)).append("라고 답변하였지.").append("\n");
    }

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n");
        recall(0);
        System.out.println(sb.toString().trim());
    }
}
