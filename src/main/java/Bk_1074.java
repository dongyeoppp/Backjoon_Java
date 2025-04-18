import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_1074 {
    // r,c에 위치하는 번호
    private static int count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        int n = Integer.parseInt(arr[0]);
        int r = Integer.parseInt(arr[1]);
        int c = Integer.parseInt(arr[2]);
        // 한변의 길이
        int size = (int) Math.pow(2,n);
        count = 0;
        find(size,r,c);
        System.out.println(count);
    }

    private static void find(int size, int r, int c){
        // r행과 c열에 해당하는 위치 찾았을 경우
        if (size == 1){
            return;
        }
        // 1사분면에 위치할 경우
        else if (r < size / 2 && c < size / 2){
            find(size/2,r,c);
        }
        // 2사분면에 위치할 경우
        else if(r<size/2 && c >= size / 2){
            find(size/2,r,c-size/2);
            count += size*size / 4;
        }
        // 3사분면에 위치할 경우
        else if (r >= size/ 2 && c < size/2){
            find(size/2,r-size/2,c);
            count += size*size / 4 * 2;
        }
        // 4사분면에 위치할 경우
        else{
            find(size/2,r-size/2,c-size/2);
            count += size*size / 4 * 3;
        }
    }
}
