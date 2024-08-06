/*
 * 프로그래머스 181187번. 두 원 사이의 정수 쌍
 * https://school.programmers.co.kr/learn/courses/30/lessons/181187
 */

import java.math.*;

class Solution {
    public long solution(long r1, long r2) {
        long answer = 0;
        
        for (long i = 1; i <= r2; i++) { 
            long maxH = (long) Math.floor(Math.sqrt(r2 * r2 - i * i));
            long minH = (r1 * r1 >= i * i) ? (long) Math.ceil(Math.sqrt(r1 * r1 - i * i)) : 0;
            answer += maxH - minH + 1;
        }        
        
        return answer * 4;
    }
}
