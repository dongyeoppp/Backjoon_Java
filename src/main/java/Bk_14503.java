import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_14503 {
    private static int[][] map;
    private static boolean[][] visited;
    private static boolean checking;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,-1,0,1};
    private static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        String[] input2 = br.readLine().split(" ");
        int r = Integer.parseInt(input2[0]);
        int c = Integer.parseInt(input2[1]);
        int d = Integer.parseInt(input2[2]);
        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i =0; i <n; i++){
            String[] input3 = br.readLine().split(" ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(input3[j]);
            }
        }
        result = 0;
        bfs(r,c,d);
        System.out.println(result);
    }
    private static void bfs(int r, int c, int d){
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {r,c});
        visited[r][c] = true;
        result++;
        int pos = 0;
        // 반시계 방향으로 회전하기 위한 방향 설정
        // pos는 dx,dy의 인덱스 값
        switch(d){
            case 0:
                break;
            case 1:
                pos = 3;
                break;
            case 2:
                pos = 2;
                break;
            case 3:
                pos = 1;
                break;
        }
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int re_r = removed[0];
            int re_c = removed[1];
            int count = 0;
            checking = false;
            while(count < 4){
                pos++;
                if(pos>=4){
                    pos = 0;
                }
                int x = re_r + dx[pos];
                int y = re_c + dy[pos];
                // 주변 4 칸 중 청소되지 않은 빈칸이 있을 경우
                // pos를 정해줬기 때문에 원래 방향에서 90도 회전한 방향에 있는 칸 부터 탐색
                if(map[x][y] == 0 && !visited[x][y]){
                    deque.add(new int[] {x,y});
                    visited[x][y] = true;
                    checking = true;
                    result++;
                    break;
                }
                count++;
            }
            // 주변 4칸 중 청소되지 않은 빈칸이 없을 경우
            if (!checking){
                // 후진 하기 위한 방향 설정
                pos = rotation_180(pos);
                int x = re_r + dx[pos];
                int y = re_c + dy[pos];
                // 벽을 만나는 후진할 수 없으므로 작동 멈춤
                if (map[x][y] == 1){
                    break;
                }
                deque.add(new int[] {x,y});
                // 후진하기 위해 방향을 바꿔줬었는데 이를 다시 또 바꿔줘야 함
                pos = rotation_180(pos);
            }
        }
    }
    // 180도 방향 회전
    private static int rotation_180(int pos){
        pos = pos+2;
        if (pos >= 4){
            pos = pos - 4;
        }
        return pos;
    }
}
