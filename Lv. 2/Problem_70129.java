/*
 * 프로그래머스 70129번. 이진 변환 반복하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/70129
 */

class Solution {

    /**
     * s가 "1"이 될 때까지 계속해서 s에 이진 변환을 가했을 때, 이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수를 구하는 메소드
     *
     * @param s 0과 1로 이루어진 문자열
     * @return . s가 "1"이 될 때까지 계속해서 s에 이진 변환을 가했을 때, 이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수
     */
    public int[] solution(String s) {
        int cnt = 0;
        int zeroCnt = 0;

        while (!s.equals("1")) {
            // x의 모든 0을 제거
            int length = s.length();
            s = s.replace("0", "");
            zeroCnt += length - s.length();

            // x의 길이를 2진법으로 표현한 문자열로 변환
            s = Integer.toBinaryString(s.length());

            // 이진 변환 횟수를 더함
            cnt++;
        }

        return new int[]{cnt, zeroCnt};
    }
}
