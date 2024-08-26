/*
 * 프로그래머스 169198번. 당구 연습
 * https://school.programmers.co.kr/learn/courses/30/lessons/169198
 */

import java.util.ArrayList;

class Solution {

    /**
     * 당구 연습 문제 풀이
     *
     * @param m      당구대의 가로 길이
     * @param n      당구대의 세로 길이
     * @param startX 머쓱이가 쳐야 하는 공이 놓인 위치 좌표 x
     * @param startY 머쓱이가 쳐야 하는 공이 놓인 위치 좌표 y
     * @param balls  매 회마다 목표로 해야하는 공들의 위치 좌표를 나타내는 정수 쌍들이 들어있는 2차원 정수배열
     * @return "원쿠션" 연습을 위해 머쓱이가 공을 적어도 벽에 한 번은 맞춘 후 목표 공에 맞힌다고 할 때, 각 회마다 머쓱이가 친 공이 굴러간 거리의 최솟값의 제곱을 담은 배열
     */
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        ArrayList<Integer> answer = new ArrayList<>();
        Position start = new Position(startX, startY);

        for (int[] ball : balls) {
            Position target = new Position(ball[0], ball[1]);
            answer.add(distance(m, n, start, target));
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    /**
     * 원쿠션으로 목표한 공을 맞췄을 때 굴러간 거리의 최솟값의 제곱을 반환하는 메소드
     *
     * @param m      당구대의 가로 길이
     * @param n      당구대의 세로 길이
     * @param start  머쓱이가 쳐야 하는 공이 놓인 위치 좌표
     * @param target 머쓱이가 맞춰야 하는 공이 놓인 위치 좌표
     * @return 원쿠션으로 목표한 공을 맞췄을 때 굴러간 거리의 최솟값의 제곱
     */
    public int distance(int m, int n, Position start, Position target) {
        // 공이 벽에 부딛혔을 때 원쿠션의 최소 거리는
        // 특정한 점(시작점 or 목표점)을 하나의 축(벽)을 기준으로 뒤집고, 다른 점과의 거리를 구했을 때 최소값이다.

        // 상, 하, 좌, 우 벽을 기준으로 뒤집었을 때의 거리를 저장할 4개의 변수
        int up = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;

        // 목표점을 위쪽 벽을 기준으로 뒤집은 경우 (목표점의 x좌표와 시작점의 x좌표가 같은 상황에서 목표점의 y좌표가 시작점의 y좌표보다 위에 있는 경우, 원쿠션을 적용할 수 없다.)
        if (!(target.x == start.x && target.y > start.y)) {
            up = start.distance(target.flipTop(n));
        }

        // 목표점을 아래쪽 벽을 기준으로 뒤집은 경우 (목표점의 x좌표와 시작점의 x좌표가 같은 상황에서 목표점의 y좌표가 시작점의 y좌표보다 아래에 있는 경우, 원쿠션을 적용할 수 없다.)
        if (!(target.x == start.x && target.y < start.y)) {
            down = start.distance(target.flipBottom(n));
        }

        // 목표점을 왼쪽 벽을 기준으로 뒤집은 경우 (목표점의 y좌표와 시작점의 y좌표가 같은 상황에서 목표점의 x좌표가 시작점의 x좌표보다 왼쪽에 있는 경우, 원쿠션을 적용할 수 없다.)
        if (!(target.y == start.y && target.x < start.x)) {
            left = start.distance(target.flipLeft(m));
        }

        // 목표점을 오른쪽 벽을 기준으로 뒤집은 경우 (목표점의 y좌표와 시작점의 y좌표가 같은 상황에서 목표점의 x좌표가 시작점의 x좌표보다 오른쪽에 있는 경우, 원쿠션을 적용할 수 없다.)
        if (!(target.y == start.y && target.x > start.x)) {
            right = start.distance(target.flipRight(m));
        }

        return Math.min(Math.min(up, down), Math.min(left, right));
    }

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 위쪽 벽(y = n)을 기준으로 뒤집은 위치를 반환하는 메소드
         *
         * @param n 당구대의 세로 길이
         * @return 위쪽 벽(y = n)을 기준으로 뒤집은 위치
         */
        public Position flipTop(int n) {
            return new Position(x, 2 * n - y);
        }

        /**
         * 아래쪽 벽(y = 0)을 기준으로 뒤집은 위치를 반환하는 메소드
         *
         * @param n 당구대의 세로 길이
         * @return 아래쪽 벽(y = 0)을 기준으로 뒤집은 위치
         */
        public Position flipBottom(int n) {
            return new Position(x, -y);
        }

        /**
         * 왼쪽 벽(x = 0)을 기준으로 뒤집은 위치를 반환하는 메소드
         *
         * @param m 당구대의 가로 길이
         * @return 왼쪽 벽(x = 0)을 기준으로 뒤집은 위치
         */
        public Position flipLeft(int m) {
            return new Position(-x, y);
        }

        /**
         * 오른쪽 벽(x = m)을 기준으로 뒤집은 위치를 반환하는 메소드
         *
         * @param m 당구대의 가로 길이
         * @return 오른쪽 벽(x = m)을 기준으로 뒤집은 위치
         */
        public Position flipRight(int m) {
            return new Position(2 * m - x, y);
        }

        /**
         * 두 점 사이의 거리의 제곱을 반환하는 메소드
         *
         * @param other 다른 점
         * @return 두 점 사이의 거리의 제곱
         */
        public int distance(Position other) {
            return (int) (Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
        }

    }
}
