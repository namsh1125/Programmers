/*
 * 프로그래머스 12918번. 문자열 다루기 기본
 * https://school.programmers.co.kr/learn/courses/30/lessons/12918
 */

import java.util.*;

class Solution {
    public boolean solution(String s) {
        if (s.length() != 4 && s.length() != 6) {
            return false;
        }

        for (char c : s.toCharArray()) {
            if (!(0 <= c - '0' && c - '0' <= 9)) {
                return false;
            }
        }

        return true;
    }
}
