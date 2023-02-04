import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N, K;
        int[] values;
        int weight, value;

        N = sc.nextInt();
        K = sc.nextInt();

        values = new int[K + 1]; //0 ~ K

        for (int n=0; n<N; n++) {
            weight = sc.nextInt();
            value = sc.nextInt();

            if(weight > K) {
                continue;
            }

            for(int i=K-weight; i>=0; i--) { //의미없는 계산 방지
                if (values[i + weight] < values[i] + value) {
                    values[i + weight] = values[i] + value;
                }
            }
        }

        System.out.println(values[K]);
    }
}
