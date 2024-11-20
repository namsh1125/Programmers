/*
 * 프로그래머스 340211번. [PCCP 기출문제] 3번 / 충돌위험 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/340211
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    /**
     * @param points 운송 포인트 n개의 좌표를 담은 2차원 정수 배열
     * @param routes 로봇 x대의 운송 경로를 담은 2차원 정수 배열 <- 로봇은 첫 포인트에서 시작해 할당된 포인트를 순서대로 방문
     * @return 모든 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수
     */
    public int solution(int[][] points, int[][] routes) {
        // 입력을 수정한다. (points 배열을 HashMap으로 변환한다.)
        HashMap<Integer, Position> pointMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            pointMap.put(i + 1, new Position(points[i][0], points[i][1]));
        }

        // 로봇이 이동할 경로를 구한다.
        ArrayList<ArrayDeque<Position>> routesList = new ArrayList<>(); // 로봇이 이동할 경로를 담은 리스트
        for (int i = 0; i < routes.length; i++) { // 로봇 x대에 대하여
            routesList.add(getRoute(pointMap, routes[i]));
        }

        // 로봇이 이동할 경로를 순회하며 충돌 위험 상황을 찾는다.
        int answer = 0; // 충돌 위험 상황의 횟수
        boolean isFinished = false; // 모든 로봇이 운송을 마쳤는지 여부

        while (!isFinished) {
            HashMap<String, Integer> visitCountMap = new HashMap<>(); // 현재 좌표에 로봇이 몇 대 있는지를 담은 HashMap (key: "{i 좌표}-{j 좌표}", value: 로봇 대수)

            // 현재 좌표에 로봇이 몇 대 있는지를 구한다.
            for (ArrayDeque<Position> route : routesList) {
                if (!route.isEmpty()) {
                    Position position = route.removeFirst();
                    String key = position.getI() + "-" + position.getJ();
                    visitCountMap.put(key, visitCountMap.getOrDefault(key, 0) + 1);
                }
            }

            // 충돌 위험 상황의 횟수를 구한다.
            for (String key : visitCountMap.keySet()) {
                if (visitCountMap.get(key) > 1) { // 현재 좌표에 로봇이 2대 이상 있는 경우
                    answer++;
                }
            }

            // 모든 로봇이 운송을 마쳤는지 여부를 구한다.
            for (ArrayDeque<Position> route : routesList) {
                if (!route.isEmpty()) { // 아직 운송 중인 로봇이 있는 경우
                    isFinished = false;
                    break;
                }

                isFinished = true;
            }
        }

        return answer;
    }

    /**
     * 로봇이 이동할 경로를 반환하는 메소드
     *
     * @param pointMap 포인트들의 좌표를 담은 HashMap
     * @param routes   로봇이 이동할 포인트를 담은 배열
     * @return 로봇이 이동할 경로
     */
    private ArrayDeque<Position> getRoute(HashMap<Integer, Position> pointMap, int[] routes) {
        ArrayDeque<Position> result = new ArrayDeque<>();

        for (int i = 0; i < routes.length - 1; i++) {
            // from에서 to로 이동하는 최적의 경로를 구한다.
            int from = routes[i]; // 시작 포인트
            int to = routes[i + 1]; // 도착 포인트
            ArrayDeque<Position> route = getRoute(pointMap.get(from), pointMap.get(to)); // from에서 to로 이동하는 최적의 경로

            // route의 경우는 start부터 end까지 이동하는 경로이므로, 첫 좌표는 제외한다. (중복 방지)
            if (i != 0) {
                route.removeFirst();
            }

            // result에 route를 추가한다.
            result.addAll(route);
        }

        return result;
    }

    /**
     * start에서 end로 이동하는 최적의 경로를 반환하는 메소드
     *
     * @param start 시작 좌표
     * @param end   도착 좌표
     * @return start에서 end로 이동하는 최적의 경로
     */
    private ArrayDeque<Position> getRoute(Position start, Position end) {
        ArrayDeque<Position> result = new ArrayDeque<>();
        int i = start.getI();
        int j = start.getJ();

        // 현재 좌표를 result에 추가한다.
        result.add(new Position(i, j));

        // i 좌표를 end의 i 좌표로 이동한다. (상하 이동)
        while (i != end.getI()) {
            if (i < end.getI()) {
                i++;
            } else {
                i--;
            }

            result.add(new Position(i, j));
        }

        // j 좌표를 end의 j 좌표로 이동한다. (좌우 이동)
        while (j != end.getJ()) {
            if (j < end.getJ()) {
                j++;
            } else {
                j--;
            }

            result.add(new Position(i, j));
        }

        return result;
    }

}

class Position {
    private int i;
    private int j;

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
