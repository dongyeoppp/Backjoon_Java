import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백트래킹을 사용하여 풀이
public class Bk_16987_1 {
    static int[][] egg;
    static int n;
    static int result;
    static boolean check;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        n = Integer.parseInt(input1);
        egg = new int[n][2];
        result = 0;
        // egg 배열에 계란의 [내구도, 무게]를 순서대로 넣음
        for (int i =0; i < n ; i++){
            String input2 = br.readLine();
            String[] arr1 =  input2.split(" ");
            egg[i][0] = Integer.parseInt(arr1[0]);
            egg[i][1] = Integer.parseInt(arr1[1]);
        }
        dfs(0);
        System.out.println(result);
    }
    static void dfs(int start){
        // 계란 치는 과정 종료, 가장 많이 깬 계란의 개수 result에 저장
        if (start == n){
            int cnt = 0;
            for(int[] arr:egg){
                if (arr[0] <= 0){
                    cnt++;
                }
            }
            result = Math.max(cnt, result);
            return;
        }
        // 손에 쥔 계란이 깨지지 않았을 때
        if (egg[start][0]>0){
            check = false;
            for (int j = 0 ; j <n; j++) {
                if (start != j && egg[j][0] > 0) {
                    egg[start][0] -= egg[j][1];
                    egg[j][0] -= egg[start][1];
                    dfs(start + 1);
                    egg[start][0] += egg[j][1];
                    egg[j][0] += egg[start][1];
                    check = true;
                }
            }
            // 손에 쥔 계란 이외에 모든 계란이 깨졌을 경우
            if (!check){
                dfs(start+1);
            }
        }
        // 손에 쥐려고 한 계란이 깨졌을 경우
        else{
            dfs(start+1);
        }
    }
}