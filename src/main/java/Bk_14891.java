import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_14891 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[4][8];
        for(int i =0; i<4;i++){
             String input = br.readLine();
             for(int j=0;j<8;j++){
                 if (input.charAt(j) == '0'){
                     arr[i][j] = 0;
                 }
                 else{
                     arr[i][j] = 1;
                 }
             }
        }
        int k = Integer.parseInt(br.readLine());
        for(int i =0;i<k;i++){
            String[] answer = br.readLine().split(" ");
            // 톱니바퀴 각각의 회전여부와 방향을 visited 배열에 저장
            int[] visited = new int[4];
            int number = Integer.parseInt(answer[0]);
            int position = Integer.parseInt(answer[1]);
            int check_right = number;
            int check_left = number-2;
            int save_right = position;
            int save_left = position;
            visited[number-1] = position;
            int right = arr[number-1][2];
            int left = arr[number-1][6];
            // 주어진 톱니바퀴의 오른쪽에 있는 톱니바퀴들의 회전 여부와 방향을 visited 배열에 저장
            while (check_right< 4 && right != arr[check_right][6]){
                save_right = save_right * -1;
                visited[check_right] = save_right;
                right = arr[check_right][2];
                check_right++;
            }
            // 주어진 톱니바퀴의 왼쪽에 있는 톱니바퀴들의 회전 여부와 방향을 visited 배열에 저장
            while (check_left>= 0 &&  left != arr[check_left][2]){
                save_left = save_left * -1;
                visited[check_left] = save_left;
                left = arr[check_left][6];
                check_left--;
            }
            // 회전 여부를 확인하여 각각의 톱니 바퀴를 회전시킴
            for(int j =0 ; j<4; j++){
                // 시계방향
                if(visited[j] == 1){
                    int save = arr[j][0];
                    for(int h = 0; h<7;h++){
                        int saving = arr[j][h+1];
                        arr[j][h+1] = save;
                        save = saving;
                    }
                    arr[j][0] = save;
                }
                // 반 시계 방향
                if (visited[j] == -1){
                    int save = arr[j][7];
                    for(int h = 7; h >0;h--){
                        int saving = arr[j][h-1];
                        arr[j][h-1] = save;
                        save = saving;
                    }
                    arr[j][7] = save;
                }
            }
        }
        int result =0;
        if (arr[0][0] == 1){
            result+=1;
        }
        if (arr[1][0] == 1){
            result+=2;
        }
        if (arr[2][0] == 1){
            result+=4;
        }
        if (arr[3][0] == 1){
            result+=8;
        }
        System.out.println(result);
    }
}
