package com.jiasxin.nowcoder.square.topic.no00001;

/**
 *
 * 链接：https://www.nowcoder.com/questionTerminal/6427b054e3a4415294b383beafc7532c?toCommentId=151414
 * 来源：牛客网
 *
 * 给你一张n*m的西湖地图二值图，其中西湖的轮廓用1表示，轮廓内核轮廓外均用0表示。
 * 现在请你统计西湖的面积，即轮廓内0的个数。
 *
 * 输入描述:
 *
 * 输入包含多组数据，每组数据第一行包含两个正整数n（3≤n≤10）和m（3≤m≤10）。
 *
 * 紧接着有n行，每行m个数字，代表地图，数字之间无空格。
 *
 * 数据保证只有一片连续的湖泊。
 *
 * 输出描述:
 *
 * 对应每一组数据，输出西湖的面积。
 *
 */
public class Test {

    public static void main(String[] args) {

        //m * n 二维地图,数字1是指定区域边界，统计边界内0的个数
        //10 10
        //0000000000
        //0001101000
        //0010010100
        //0010000010
        //0100000010
        //0100000100
        //0010001000
        //0010001000
        //0011010000
        //0000100000

        int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
        };

        int m = 10;
        int n = 10;

        System.out.println("----------填充前--------");
        printMap(map, m, n);

        map = handleMapPadding(map, m, n);

        System.out.println("----------填充后--------");
        printMap(map, m, n);

        //统计边界内0的个数
        int zeroNum = getMapZeroNum(map, m, n);

        System.out.println(zeroNum);

    }

    private static int[][] handleMapPadding(int[][] map, int m, int n) {

        int i,j;

        for (i = 0; i < m; ++i) {
            if (map[i][0] == 0) travel(map, i, 0);
            if (map[i][n - 1] == 0) travel(map, i, n - 1);
        }

        for (j = 0; j < n; ++j) {
            if (map[0][j] == 0) travel(map, 0, j);
            if (map[m - 1][j] == 0) travel(map, m - 1, j);
        }

        return map;

    }

    private static void travel(int[][] map, int x, int y) {
        int m = map.length, n = map[0].length;
        map[x][y] = 2;

        System.out.println("----------填充过程--------");
        printMap(map, m, n, x, y);

        if (x > 0 && map[x - 1][y] == 0) travel(map, x - 1, y);
        if (x < m - 1 && map[x + 1][y] == 0) travel(map, x + 1, y);
        if (y > 0 && map[x][y - 1] == 0) travel(map, x, y - 1);
        if (y < n - 1 && map[x][y + 1] == 0) travel(map, x, y + 1);
    }

    private static int getMapZeroNum(int[][] map, int m, int n) {

        int zeroNum = 0;

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (map[i][j] == 0) ++zeroNum;

        return zeroNum;

    }

    private static void printMap(int[][] map, int m, int n) {
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(map[i][j] + ",");
            }
            System.out.println();
        }
    }

    private static void printMap(int[][] map, int m, int n, int x, int y) {
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if( (i==x) && (j==y) ){
                    System.out.print("#,");
                }else{
                    System.out.print(map[i][j] + ",");
                }
            }
            System.out.println();
        }
    }

}
