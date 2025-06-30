import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bk_15656 {
    private static StringBuilder sb = new StringBuilder();
    private static int n,m;
    private static int[] arr, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        String[] input1 = br.readLine().split(" ");
        arr = new int[n];
        for(int i = 0; i<n;i++){
            arr[i] = Integer.parseInt(input1[i]);
        }
        Arrays.sort(arr);
        // 수열을 저장할 배열을 만들어 놓음
        answer = new int[m];
        // 매개변수로 배열에 저장한 자연수의 개수를 넣음
        dfs(0);
        System.out.println(sb.toString().trim());
    }
    private static void dfs(int cnt){
        // 재귀 종료 조건
        if (cnt == m){
            for(int num: answer){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=0;i<n;i++){
            // answer 배열을 계속 갱신
            answer[cnt] = arr[i];
            cnt++;
            dfs(cnt);
            cnt--;
        }
    }
}
