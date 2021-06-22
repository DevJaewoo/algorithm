import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    private static final boolean DEBUG = false;

    private static char[][] Map;            //맵, '.', 'X', 'L'로 구성되어있다.
    private static boolean[][] CheckedMap;  //백조 범위 체크 맵, 체크 여부에 따라 true, false 가 저장된다.

    private static LinkedList<Point> SwanQueue, WaterQueue; //SwanQueue: 백조 범위 테두리, WaterQueue: 물 테두리
    private static int WIDTH, HEIGHT;                       //맵 너비, 높이

    private static Point tmpPoint;  //다용도 포인트 변수

    private static final int[] dirX = {-1, 1, 0, 0};        // 좌/우/상/하로
    private static final int[] dirY = {0, 0, 1, -1};        // 움직일때 증가량

    private static void printMap() {
        if(DEBUG) {
            for (char[] line : Map) {
                System.out.println(line);
            }
            System.out.println();
        }
    }

    private static void printDebugMsg(String msg) {
        if(DEBUG) {
            System.out.println(msg);
        }
    }

    private static void initMap() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();

        HEIGHT = Integer.parseInt(str.split(" ")[0]);
        WIDTH = Integer.parseInt(str.split(" ")[1]);
        printDebugMsg("Width: " + WIDTH + ", HEIGHT: " + HEIGHT);

        Map = new char[HEIGHT][WIDTH];
        CheckedMap = new boolean[HEIGHT][WIDTH];

        SwanQueue = new LinkedList<>();
        WaterQueue = new LinkedList<>();

        for(int y=0; y<HEIGHT; y++) {
            bufferedReader.read(Map[y]);
            bufferedReader.read(); // Ignore '\n'

            for(int x=0; x<WIDTH; x++) {
                if (Map[y][x] != 'X') {
                    WaterQueue.add(new Point(x, y));
                }
                if(Map[y][x] == 'L') {
                    tmpPoint = new Point(x, y);
                }
            }
        }

        SwanQueue.add(tmpPoint);
        printMap();
    }

    private static boolean updateSwan() {

        for (int t = SwanQueue.size(); t > 0; t--) {
            tmpPoint = SwanQueue.remove();
            CheckedMap[tmpPoint.y][tmpPoint.x] = true;

            printDebugMsg("Swan Pop X:" + tmpPoint.x + " Y:" + tmpPoint.y);

            for(int d=0; d<4; d++) {
                int x = tmpPoint.x + dirX[d];
                int y = tmpPoint.y + dirY[d];

                if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) continue;
                if(CheckedMap[y][x]) continue;

                switch (Map[y][x]) {
                    case '.':
                        CheckedMap[y][x] = true;
                        SwanQueue.push(new Point(x, y));
                        t++;
                        break;

                    case 'X':
                        CheckedMap[y][x] = true;
                        SwanQueue.add(new Point(x, y));
                        break;

                    case 'L':
                        return true;
                }
            }
        }
        return false;
    }

    private static void updateWater() {

        for(int t=WaterQueue.size(); t>0; t--) {
            tmpPoint = WaterQueue.remove();
            printDebugMsg("Water Pop X:" + tmpPoint.x + " Y:" + tmpPoint.y);

            for(int d=0; d<4; d++) {
                int x = tmpPoint.x + dirX[d];
                int y = tmpPoint.y + dirY[d];

                if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) continue;

                if(Map[y][x] == 'X') {
                    Map[y][x] = '.';
                    WaterQueue.add(new Point(x, y));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int day = 0;
        initMap();

        while (!updateSwan()) {
            updateWater();
            printMap();
            day++;
        }

        System.out.println(day);
    }
}
