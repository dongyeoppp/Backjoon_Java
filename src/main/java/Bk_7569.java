import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_7569 {
    private static int[][][] map;
    private static int[] dx = {1,-1,0,0,0,0};
    private static int[] dy = {0,0,1,-1,0,0};
    private static int[] dz = {0,0,0,0,1,-1};
    private static int n,m,h;
    private static Deque<int[]> deque = new ArrayDeque<>();

    private static void bfs(){
        while(!deque.isEmpty()){
            int[] arr = deque.poll();
            for (int i =0; i < 6; i++){
                int x = dx[i]+arr[0];
                int y = dy[i]+arr[1];
                int z = dz[i]+arr[2];
                // 익지 않은 토마토일 경우만, 익은 토마토일 경우 0이 아닌 수로 방문 체크 가능하므로 visited 배열을 사용하지 않음
                if (0<=x && x<n && 0<= y && y<m && 0<=z && z<h && map[z][x][y] == 0){
                    map[z][x][y] = map[arr[2]][arr[0]][arr[1]] + 1;
                    deque.add(new int[] {x,y,z});
                }
            }
        }
    }

    private static int find(){
        int save = 1;
        for(int i =0 ; i<h; i++){
            for (int j=0; j<n; j++){
                for(int k = 0; k<m; k++){
                    // 익지 않은 토마토가 존재 할 경우
                    if (map[i][j][k] == 0){
                        return -1;
                    }
                    // 토마토가 익는데 걸리는 최소 시간 저장
                    else{
                        save = Math.max(map[i][j][k], save);
                    }
                }
            }
        }
        return save-1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);
        h = Integer.parseInt(input[2]);
        map = new int[h][n][m];
        for(int i =0 ; i<h; i++){
            for (int j=0; j<n; j++){
                String[] input1 = br.readLine().split(" ");
                for(int k = 0; k<m; k++){
                    map[i][j][k] = Integer.parseInt(input1[k]);
                    // 토마토가 존재하는 곳의 좌표를 먼저 넣어주어야 한다.
                    // 어느 한 곳의 좌표만 que에 먼저 넣고 bfs를 하면 최단 거리가 좌표에 적히지 않을 수 있다.
                    if (map[i][j][k] == 1){
                        deque.add(new int[] {j,k,i});
                    }
                }
            }
        }
        // 토마토가 있는 곳의 좌표를 que에 넣어놓고 bfs 실행
        bfs();
        System.out.println(find());
    }
}
