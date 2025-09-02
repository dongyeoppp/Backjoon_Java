import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_3190 {
    public static boolean[][] apple;
    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {1,0,-1,0};
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        apple = new boolean[n][n];
        for(int i=0; i<k; i++){
            String[] input1 = br.readLine().split(" ");
            int row = Integer.parseInt(input1[0]);
            int col = Integer.parseInt(input1[1]);
            apple[row-1][col-1] = true;
        }
        int l = Integer.parseInt(br.readLine());
        // 초와 방향을 담는 큐를 각자 만듬
        Deque<Integer> sec = new ArrayDeque<>();
        Deque<String> position = new ArrayDeque<>();
        for(int i=0; i<l; i++){
            String[] input2 = br.readLine().split(" ");
            sec.add(Integer.parseInt(input2[0]));
            position.add(input2[1]);
        }
        int count = 0;
        int pos = 0;
        int row = 0;
        int col = 0;
        // 현재 뱀이 위치한 좌표를 bem에 저장
        Deque<Integer> bem = new ArrayDeque<>();
        bem.add(0);
        while(true){
            count++;
            row += dx[pos];
            col += dy[pos];
            // 현재 시간과 뱀의 방향 전환 시간 비교
            if (!sec.isEmpty() && sec.peek() == count){
                sec.poll();
                String new_pos = position.poll();
                // string 객체는 equals로 비교
                if (new_pos.equals("D")){
                    pos++;
                    if (pos >= 4){
                        pos = 0;
                    }
                }
                else if (new_pos.equals("L")){
                    pos--;
                    if(pos<0){
                        pos = 3;
                    }
                }
            }
            // 뱀이 벽을 만나거나, 자기 자신과 부딪혔을 경우 게임 끝
            if (0 > row || row >= n || 0 > col || col >=n || bem.contains(row*n+col)){
                break;
            }
            // 이동한 칸에 사과가 없을 경우
            if (!apple[row][col]){
                bem.poll();
            }
            // 이동한 칸에 사과가 있을 경우
            if (apple[row][col]){
                apple[row][col] = false;
            }
            bem.add(row*n+col);
        }
        System.out.println(count);
    }
}
