import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_16985 {
    private static boolean[] check;
    private static int result;
    private static int[][][] visited;
    private static int[] save_order, save_pos;
    private static int[] dx = {1,-1,0,0,0,0};
    private static int[] dy = {0,0,1,-1,0,0};
    private static int[] dz = {0,0,0,0,1,-1};
    private static int[][][] map, copy_map;
    private static boolean checking;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5][5];
        for(int i =0; i<5; i++){
            for (int j=0; j<5; j++){
                String[] input = br.readLine().split(" ");
                for(int h=0; h<5; h++){
                    map[i][j][h] = Integer.parseInt(input[h]);
                }
            }
        }
        // 1) 판 순서 정렬
        // 2) 각 판의 방향 정하기
        // 3) bfs 거리 탐색
        copy_map = new int[5][5][5];
        check = new boolean[5];
        save_order = new int[5];
        save_pos = new int[5];
        result = Integer.MAX_VALUE;
        per(0);
        // 탈출이 불가능할 경우
        if (!checking){
            result = -1;
        }
        System.out.println(result);
    }
    // 순열
    // 판의 순서의 경우의 수를 save_order에 저장
    private static void per(int count){
        if(count == 5){
            // 판의 방향 설정하기
            rotation(0);
            return;
        }
        for(int i=0;i<5;i++){
            if (!check[i]){
                check[i]= true;
                save_order[count] = i;
                per(count+1);
                check[i]=false;
            }
        }
    }
    // 중복 순열
    // 판의 방향을 save_pos에 저장한 이후, copy_map에 새로운 미로를 만듬
    // save_pos에는 0,1,2,3 중 하나를 각 인덱스에 저장
    // 0은 0도, 1은 90도, 2는 180도, 3은 270도 시계방향 회전을 의미
    private static void rotation(int count){
        if(count == 5){
            for(int i=0; i<5; i++){
                if (save_pos[i] == 0){
                    for(int j=0;j<5;j++){
                        for(int k=0; k<5; k++){
                            copy_map[i][j][k] = map[save_order[i]][j][k];
                        }
                    }
                }
                else if (save_pos[i] == 1){
                    for(int j=0;j<5;j++){
                        for(int k=0; k<5; k++){
                            copy_map[i][k][4-j] = map[save_order[i]][j][k];
                        }
                    }
                }
                else if (save_pos[i] == 2){
                    for(int j=0;j<5;j++){
                        for(int k=0; k<5; k++){
                            copy_map[i][4-j][4-k] = map[save_order[i]][j][k];
                        }
                    }
                }
                else if (save_pos[i] == 3){
                    for(int j=0;j<5;j++){
                        for(int k=0; k<5; k++){
                            copy_map[i][4-k][j] = map[save_order[i]][j][k];
                        }
                    }
                }
            }
            // 새로 만든 미로로 bfs() 수행
            if(copy_map[0][0][0] == 1 && copy_map[4][4][4] == 1){
                int answer = bfs();
                if(answer != -1 && answer < result){
                    // 탈출 가능한 미로일 경우 최단거리 갱신
                    result = answer;
                    checking = true;
                }
            }
            return;
        }
        for(int i =0; i<4; i++){
            save_pos[count] = i;
            rotation(count+1);
        }
    }
    // bfs를 통해 미로의 최단거리를 체크
    // visited 배열에 거리를 저장하며 최단거리를 구함
    private static int bfs(){
        Deque<int[]> deque = new ArrayDeque<>();
        visited = new int[5][5][5];
        deque.add(new int[] {0,0,0});
        visited[0][0][0] = 1;
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int a = removed[0];
            int b = removed[1];
            int c = removed[2];
            if (a == 4 && b == 4 && c == 4){
                return visited[a][b][c]-1;
            }
            for(int i =0; i<6;i++){
                int z = a + dz[i];
                int x = b + dx[i];
                int y = c + dy[i];
                if(0<=z && z<5 && 0<=x && x<5 && 0<=y && y<5 && visited[z][x][y]==0 && copy_map[z][x][y] == 1){
                    deque.add(new int[] {z,x,y});
                    visited[z][x][y] = visited[a][b][c]+1 ;
                }
            }
        }
        return -1;
    }
}
