/*
 * 프로그래머스 67256번. 키패드 누르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/67256
 */

class Solution {

    /**
     * 키패드 누르기
     *
     * @param numbers 순서대로 누를 번호가 담긴 배열
     * @param hand    왼손잡이인지 오른손잡이인 지를 나타내는 문자열
     * @return 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열
     */
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        // 왼손, 오른손의 현재 위치
        int left = 10;
        int right = 12;

        // 누를 번호를 돌면서
        for (int number : numbers) {
            // 1, 4, 7인 경우엔 왼손으로 입력하고
            // 3, 6, 9인 경우엔 오른손으로 입력한다.
            // 2, 5, 8, 0인 경우엔 현재 왼손과 오른손의 위치와의 거리를 계산하여
            // 더 가까운 손으로 입력한다.

            if (number == 1 || number == 4 || number == 7) {
                answer.append("L");
                left = number;

            } else if (number == 3 || number == 6 || number == 9) {
                answer.append("R");
                right = number;

            } else {
                // 0의 경우 11로 치환
                if (number == 0) {
                    number = 11;
                }

                String handToUse = getHand(left, right, number, hand);

                if (handToUse.equals("left")) {
                    answer.append("L");
                    left = number;

                } else {
                    answer.append("R");
                    right = number;
                }
            }
        }

        return answer.toString();
    }

    /**
     * 2, 5, 8, 0인 경우 어떤 손으로 입력할 지 결정하는 메소드
     */
    private String getHand(int left, int right, int number, String hand) {
        // 왼손과 오른손의 거리 비교
        int leftDistance = getDistance(left, number);
        int rightDistance = getDistance(right, number);

        // 거리가 같은 경우 손잡이에 따라 결정
        if (leftDistance == rightDistance) {
            return hand;
        }

        return leftDistance < rightDistance ? "left" : "right";
    }

    /**
     * 두 번호 사이의 거리를 계산하는 메소드
     *
     * @param start 시작 번호
     * @param end   끝 번호
     * @return 두 번호 사이의 거리
     */
    private int getDistance(int start, int end) {

        // 번호 1부터 12까지의 위치를 2차원 배열로 표현
        int[][] keypad = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2},
                {3, 0}, {3, 1}, {3, 2}
        };

        // 시작 번호와 끝 번호의 위치를 구한다.
        int[] startLocation = keypad[start - 1];
        int[] endLocation = keypad[end - 1];

        // 두 번호 사이의 거리를 계산한다.
        return Math.abs(startLocation[0] - endLocation[0]) + Math.abs(startLocation[1] - endLocation[1]);
    }
}
