/*
 * 프로그래머스 12899번. 124 나라의 숫자
 * https://school.programmers.co.kr/learn/courses/30/lessons/12899
 */

class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int remainder = n % 3;
            n /= 3;

            if (remainder == 0) {
                n--;
                sb.insert(0, 4);

            } else if (remainder == 1) {
                sb.insert(0, 1);

            } else {
                sb.insert(0, 2);
            }
        }

        return sb.toString();
    }
}
