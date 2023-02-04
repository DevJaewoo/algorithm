import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Integer> arrayList = new ArrayList<>();
        int N, num;
        int min, max, index, mid;

        N = Integer.parseInt(bufferedReader.readLine());

        for (int n = 0; n < N; n++) {
            num = Integer.parseInt(bufferedReader.readLine());

            min = 0;
            max = n - 1;

            //이분탐색을 통해 숫자가 들어가야 할 인덱스 구하기
            while(min <= max) {
                index = (min + max) / 2;
                mid = arrayList.get(index);

                if(mid < num) {
                    min = index + 1;
                }
                else if(mid > num) {
                    max = index - 1;
                }
                else {
                    min = index;
                    break;
                }
            }

            arrayList.add(min, num);
            bufferedWriter.write(arrayList.get(n / 2) + "\n");
        }

        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
