/*
 * 프로그래머스 12917번. 문자열 내림차순으로 배치하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12917
 */

import java.util.*;

class Solution {
    public String solution(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        return new StringBuilder(new String(chars)).reverse().toString();
    }
}
