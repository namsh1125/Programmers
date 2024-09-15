/*
 * 프로그래머스 42746번. 가장 큰 수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42746
 */

import java.util.Arrays;

class Solution {

    /**
     * 주어진 숫자 배열을 조합하여 만들 수 있는 가장 큰 수를 반환하는 메소드
     *
     * @param numbers 0 또는 양의 정수가 담긴 배열
     * @return 순서를 재배치하여 만들 수 있는 가장 큰 수 (문자열)
     */
    public String solution(int[] numbers) {
        // int 배열을 String 배열로 변환
        String[] strNumbers = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        // 정렬 (정렬 기준 - 두 수를 합쳤을 때 큰 수가 앞으로 오도록)
        Arrays.sort(strNumbers, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        // 가장 큰 수를 반환
        if (strNumbers[0].equals("0")) {
            return "0";
        } else {
            return String.join("", strNumbers);
        }
    }
}
