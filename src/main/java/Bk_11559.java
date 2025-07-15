import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_11559 {
    private static int[] dx = {0,0,1,-1};
    private static int[] dy = {1,-1,0,0};
    private static Character[][] arr;
    private static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new Character[12][6];
        for(int i=0;i<12;i++){
            String input = br.readLine();
            for(int j =0; j<6;j++){
                arr[i][j] = input.charAt(j);
            }
        }
        // 터질 수 있는 뿌요가 있는지를  check를 통해 확인
        boolean check = true;
        int result = 0;
        while(check){
            for(int i =0; i<12;i++){
                for(int j =0; j<6;j++){
                    if (arr[i][j] != '.'){
                        // bfs를 통해 4개 이상 모여 있는 뿌요 여부 확인
                        if (bfs(i,j) >= 4){
                            // 뿌요 터트리기
                            pung();
                            check = false;
                        }
                    }
                }
            }
            result++;
            // 더 이상 터질 뿌요가 없을 경우
            if(check){
                check = false;
                result--;
            }
            else{
                // 뿌요가 터진 이후 중력 적용
                change();
                check = true;
            }
        }
        System.out.println(result);
    }
    private static int bfs(int row,int col){
        Deque<int[]> que = new ArrayDeque<>();
        visited = new boolean[12][6];
        que.add(new int[] {row,col});
        visited[row][col] = true;
        int answer = 1;
        char save = arr[row][col];
        while(!que.isEmpty()){
            int[] removed = que.poll();
            for(int i=0; i<4; i++){
                int x = removed[0] + dx[i];
                int y = removed[1] + dy[i];
                if(0<= x && x < 12 && 0<= y && y < 6 && !visited[x][y] && arr[x][y] == save){
                    que.add(new int[] {x,y});
                    visited[x][y] = true;
                    answer++;
                }
            }
        }
        return answer;
    }
    // 4개 이상 모여있는 뿌요 자리에 알파벳을 '.' 으로 변경
    private static void pung(){
        for(int i=0; i< 12; i++){
            for(int j=0; j<6; j++){
                if (visited[i][j]){
                    arr[i][j]= '.';
                }
            }
        }
    }
    //  터진 뿌요의 자리를 고려한 중력 적용
    private static void change(){
        // 0 부터 하면 적용 안되는 자리가 생길 수 있으므로 거꾸로 탐색해야 함
        for(int i=11; i>=0; i--){
            for(int j=0; j<6; j++){
                if (arr[i][j] != '.'){
                    int x = i;
                    while (x+1 < 12 && arr[x+1][j] == '.'){
                        arr[x+1][j] = arr[x][j];
                        arr[x][j] = '.';
                        x++;
                    }
                }
            }
        }
    }
}
