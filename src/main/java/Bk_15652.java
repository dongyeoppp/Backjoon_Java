import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_15652 {
    static int n;
    static int m;
    static int[] arr1;
    static StringBuilder sb = new StringBuilder();
    static int cnt;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        n = Integer.parseInt(arr[0]);
        m = Integer.parseInt(arr[1]);
        arr1 = new int[n];
        cnt =1;
        dfs(0);
        System.out.print(sb);
    }

    private static void dfs(int count){
        if (count == m){
            for(int num: arr1){
                if (num == 0){
                    break;
                }
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i =1 ; i <=n ; i++){
            // arr1 배열에서 비 내림차순을 위반하는 경우는 재귀를 하지 못하게 조정했습니다.
            if (count>0 && arr1[count-1] > i){
                continue;
            }
            arr1[count] = i;
            dfs(count+1);
        }
    }
}
