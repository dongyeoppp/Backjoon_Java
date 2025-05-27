import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_1600 {
    public static int[][] map;
    public static int k,w,h;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int[] horse_dx = {-2,-1,1,2,2,1,-1,-2};
    public static int[] horse_dy = {1,2,2,1,-1,-2,-2,-1};
    public static int result;
    public static int bfs(){
        Deque<int[]> deque = new ArrayDeque<>();
        // visited의 두번째 인덱스는 말처럼 이동한 횟수를 의미한다.
        int[][][] visited = new int[h][w][k+1];
        // deque에도 row, col, 말처럼 이동한 횟수를 넘기며 체크한다.
        deque.add(new int[] {0,0,0});
        // visited 안에는 최소 거리를 갱신하여 저장한다.
        visited[0][0][0] = 1;
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int row = removed[0];
            int col = removed[1];
            int cnt = removed[2];
            // 도착점에 도착했을 경우 . 최단 거리 return
            if (row == h-1 && col == w-1){
                 return visited[h-1][w-1][cnt]-1;
            }
            // 상하좌우 인접한 곳으로 이동할 경우
            for(int i=0;i<4;i++){
                int x = dx[i] + row;
                int y = dy[i] + col;
                if (0<= x && x < h && 0<=y && y<w && map[x][y] == 0 && visited[x][y][cnt] == 0){
                    deque.add(new int[] {x,y,cnt});
                    visited[x][y][cnt] = visited[row][col][cnt] + 1;
                }
            }
            // 말 처럼 움직였을 경우
            for(int i=0;i<8;i++){
                int horse_x = horse_dx[i]+row;
                int horse_y = horse_dy[i]+col;
                // visited의 두번째 인덱스 값이 k를 초과하지 않도록 한다.
                if (0<= horse_x && horse_x < h && 0<=horse_y && horse_y<w && cnt+1 <= k && map[horse_x][horse_y] == 0 && visited[horse_x][horse_y][cnt+1] == 0){
                    deque.add(new int[] {horse_x,horse_y,cnt+1});
                    // 말의 움직임을 체크하기 위해 visited의 두번째 인덱스값 +1로 갱신
                    visited[horse_x][horse_y][cnt+1] = visited[row][col][cnt] + 1;
                }
            }
        }
        // 도착점에 도착하지 못할 경우 -1 return
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        String[] input1 = br.readLine().split(" ");
        w = Integer.parseInt(input1[0]);
        h = Integer.parseInt(input1[1]);
        map = new int[h][w];
        for(int i=0;i<h;i++){
            String[] input2 = br.readLine().split(" ");
            for (int j=0;j<w;j++){
                map[i][j] = Integer.parseInt(input2[j]);
            }
        }
        System.out.println(bfs());
    }
}
