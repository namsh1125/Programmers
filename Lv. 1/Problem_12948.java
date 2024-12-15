/*
 * 프로그래머스 12948번. 핸드폰 번호 가리기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12948
 */

class Solution {
    public String solution(String phoneNumber) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (i < phoneNumber.length() - 4) {
                sb.append("*");
            } else {
                sb.append(phoneNumber.charAt(i));
            }
        }

        return sb.toString();
    }
}
