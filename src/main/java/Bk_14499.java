import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_14499 {
    public static int[] arr;
    public static int[][] map;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int x = Integer.parseInt(input[2]);
        int y = Integer.parseInt(input[3]);
//        int k = Integer.parseInt(input[4]);
        map = new int[n][m];
        // 각각의 arr 인덱스 마다 주사위의 위치에 저장된 숫자를 표시
        // 순서대로 주사위 맨위, 북, 동, 서, 남, 주사위 맨 아래를 표시
        arr = new int[] {0, 0, 0, 0, 0, 0};
        for(int i =0; i<n; i++){
            String[] input1 = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(input1[j]);
            }
        }
        String[] order = br.readLine().split(" ");
        for(String str: order){
            int position = Integer.parseInt(str);
            // 명령에 따른 switch-case문 사용, break를 사용하지 않으면 switch 구문이 끝나지 않으므로 주의해야 함
            // if 문을 사용했었으나 이게 더 깔끔한것 같아서 수정함
            switch(position){
                case 1:
                    y++;
                    if (y>=m){
                        y--;
                        continue;
                    }
                    rolling(position);
                    copy_number(x,y);
                    break;
                case 2:
                    y--;
                    if (y<0){
                        y++;
                        continue;
                    }
                    rolling(position);
                    copy_number(x,y);
                    break;
                case 3:
                    x--;
                    if (x<0){
                        x++;
                        continue;
                    }
                    rolling(position);
                    copy_number(x,y);
                    break;
                case 4:
                    x++;
                    if (x>=n){
                        x--;
                        continue;
                    }
                    rolling(position);
                    copy_number(x,y);
                    break;
            }
        }
        System.out.println(sb.toString().trim());
    }
    // 주사위가 굴러가는 것을 고려하여 숫자의 위치를 조정
    // 동,서,북,남 으로 주사위가 굴러가는 것을 고려함
    public static void rolling(int position){
        int save = arr[0];
        switch(position){
            case 1:
                arr[0] = arr[3];
                arr[3] = arr[5];
                arr[5] = arr[2];
                arr[2] = save;
                break;
            case 2:
                arr[0] = arr[2];
                arr[2] = arr[5];
                arr[5] = arr[3];
                arr[3] = save;
                break;
            case 3:
                arr[0] = arr[4];
                arr[4] = arr[5];
                arr[5] = arr[1];
                arr[1] = save;
                break;
            case 4:
                arr[0] = arr[1];
                arr[1] = arr[5];
                arr[5] = arr[4];
                arr[4] = save;
                break;
        }
    }
    // 주사위가 굴러간 이후 지도와 주사위의 숫자 조건에 맞게 조정
    //
    public static void copy_number(int x, int y){
        if (map[x][y] == 0){
            map[x][y] = arr[5];
        }
        else{
            arr[5] = map[x][y];
            // 칸에 쓰여있는 숫자는 0이 되는 조건을 빼먹어서 틀렸었음
            map[x][y] = 0;
        }
        // 숫자 조정 이후 출력문에 주사위 맨위 숫자 저장
        sb.append(arr[0]).append("\n");
    }
}
