/*
 * 프로그래머스 42860번. 조이스틱
 * https://school.programmers.co.kr/learn/courses/30/lessons/42860
 */

class Solution {

    /**
     * 조이스틱을 이용하여 이름을 만들 때, 조이스틱 조작 횟수의 최솟값을 구하는 메소드
     *
     * @param name 만들고자 하는 이름
     * @return 이름에 대해 조이스틱 조작 횟수의 최솟값
     */
    public int solution(String name) {
        int changeCount = 0;
        int minMoveCount = name.length() - 1;
        int current = 0;

        while (current < name.length()) {
            // 알파뱃 변경 횟수 계산
            char c = name.charAt(current);
            changeCount += getMoveCount('A', c);

            // 변경할 알파뱃 위치 계산
            int nextPos = current + 1;
            while (nextPos < name.length() && name.charAt(nextPos) == 'A') {
                nextPos++;
            }

            // 최소 이동 횟수 계산
            minMoveCount = Math.min(minMoveCount, current * 2 + (name.length() - nextPos)); // current까지 우측으로 먼저 이동하고, nextPos까지 좌측으로 이동
            minMoveCount = Math.min(minMoveCount, (name.length() - nextPos) * 2 + current); // nextPos까지 좌측으로 먼저 이동하고, current까지 우측으로 이동

            // 다음 알파뱃으로 이동
            current = nextPos;
        }

        return changeCount + minMoveCount;
    }


    /**
     * 조이스틱을 움직여 원하는 알파뱃으로 변경하기 위해 조이스틱을 움직이는 횟수를 구하는 메소드
     *
     * @param from 현재 알파뱃
     * @param to   원하는 알파뱃
     * @return 조이스틱을 움직이는 횟수
     */
    private int getMoveCount(char from, char to) {
        int diff = to - from; // 위로 이동하는 경우
        if (diff > 13) { // 아래로 이동하는 경우
            diff = 26 - diff;
        }
        return diff;
    }
}
