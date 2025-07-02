import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bk_15666 {
    private static int n,m;
    private static int[] arr, answer;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);
        arr = new int[n];
        String[] input2 = br.readLine().split(" ");
        for(int i =0;i<n;i++){
            arr[i] = Integer.parseInt(input2[i]);
        }
        answer = new int[m];
        Arrays.sort(arr);
        dfs(0);
        System.out.println(sb.toString().trim());
    }
    // 수의 중복은 허용 하므로 visited 배열은 사용하지 않음
    // 수열의 중복을 방지하기 위해 before 변수를 사용하여 중복을 방지
    private static void dfs(int cnt){
        if (cnt == m){
            for(int num:answer){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        int before = 0;
        for(int i = 0; i<n; i++){
            // 수열의 비내림차순 조건 만족을 위해 answer 배열의 값들 비교
            if(cnt>0 && answer[cnt-1] > arr[i]){
                continue;
            }
            if (arr[i]!=before){
                answer[cnt] = arr[i];
                cnt++;
                dfs(cnt);
                cnt--;
                before = answer[cnt];
            }
        }
    }
}
