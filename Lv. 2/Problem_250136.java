/*
 * 프로그래머스 250136번. [PCCP 기출문제] 2번 / 석유 시추
 * https://school.programmers.co.kr/learn/courses/30/lessons/250136
 */

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

class Solution {

    private final static int PETROLEUM = 1; // 석유
    private final static int[] di = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    private final static int[] dj = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public int solution(int[][] land) {
        int[][] group = new int[land.length][land[0].length];
        HashMap<Integer, Integer> groupArea = grouping(land, group);

        int answer = 0;
        for (int j = 0; j < land[0].length; j++) {
            HashSet<Integer> groupSet = new HashSet<>();

            // j번째 열에 있는 석유 덩어리를 구함
            for (int i = 0; i < land.length; i++) {
                if (group[i][j] != 0) {
                    groupSet.add(group[i][j]);
                }
            }

            // 석유 덩어리의 면적을 구함
            int sum = 0;
            for (int groupNumber : groupSet) {
                sum += groupArea.get(groupNumber);
            }

            // 석유 덩어리의 면적의 최댓값을 구함
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    /**
     * 석유 덩어리를 그룹핑하는 메소드
     *
     * @param land  석유가 묻힌 땅과 석유 덩어리를 나타내는 2차원 정수 배열
     * @param group 석유 덩어리를 그룹핑한 2차원 정수 배열
     * @return 석유 덩어리의 그룹 번호와 그룹의 면적을 저장한 HashMap
     */
    private HashMap<Integer, Integer> grouping(int[][] land, int[][] group) {
        HashMap<Integer, Integer> groupArea = new HashMap<>();

        int groupNumber = 1;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == PETROLEUM && group[i][j] == 0) {
                    int area = bfs(land, group, i, j, groupNumber);
                    groupArea.put(groupNumber, area);
                    groupNumber++;
                }
            }
        }

        return groupArea;
    }

    /**
     * 석유 덩어리를 BFS로 탐색하는 메소드
     *
     * @param land        석유가 묻힌 땅과 석유 덩어리를 나타내는 2차원 정수 배열
     * @param group       석유 덩어리를 그룹핑한 2차원 정수 배열
     * @param i           행
     * @param j           열
     * @param groupNumber 그루핑할 석유 덩어리의 그룹 번호
     * @return (i, j) 위치의 석유 덩어리의 면적
     */
    private int bfs(int[][] land, int[][] group, int i, int j, int groupNumber) {
        ArrayDeque<Position> queue = new ArrayDeque<>();
        queue.add(new Position(i, j));
        group[i][j] = groupNumber;

        int area = 1;
        while (!queue.isEmpty()) {
            Position current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ni = current.getI() + di[d];
                int nj = current.getJ() + dj[d];

                if (!isInRange(land, ni, nj)) {
                    continue;
                }

                if (land[ni][nj] == PETROLEUM && group[ni][nj] == 0) {
                    queue.add(new Position(ni, nj));
                    group[ni][nj] = groupNumber;
                    area++;
                }
            }
        }

        return area;
    }

    /**
     * land 배열의 범위를 벗어나는지 확인하는 메소드
     *
     * @param land 석유가 묻힌 땅과 석유 덩어리를 나타내는 2차원 정수 배열
     * @param i    행
     * @param j    열
     * @return 범위를 벗어나면 false, 그렇지 않으면 true
     */
    private boolean isInRange(int[][] land, int i, int j) {
        return i >= 0 && i < land.length && j >= 0 && j < land[0].length;
    }
}

class Position {
    private int i; // 행
    private int j; // 열

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
