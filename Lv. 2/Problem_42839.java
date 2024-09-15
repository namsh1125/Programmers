/*
 * 프로그래머스 42839번. 소수 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 */

import java.util.ArrayList;
import java.util.HashSet;

class Solution {

    /**
     * 입력받은 종이 조각으로 만들 수 있는 소수의 개수를 반환하는 메소드
     *
     * @param numbers 각 종이 조각에 적힌 숫자가 적힌 문자열 (1 <= numbers 길이 <= 7, 0 <= numbers 원소 <= 9)
     * @return 종이 조각으로 만들 수 있는 소수의 개수
     */
    public int solution(String numbers) {
        ArrayList<Integer> number = new ArrayList<>();
        for (char c : numbers.toCharArray()) {
            number.add(c - '0');
        }

        int answer = 0; // 종이 조각으로 만들 수 있는 소수의 개수
        for (int num : getNumbers(number, 0, 0, new boolean[number.size()])) {
            if (isPrimeNumber(num)) {
                answer++;
            }
        }

        return answer;
    }

    /**
     * 숫자 조합을 만드는 메소드
     *
     * @param numberList 종이 조각에 적힌 숫자 리스트
     * @param number     현재까지 만든 숫자
     * @param depth      현재까지 탐험한 깊이
     * @param visited    숫자 사용 여부
     * @return 숫자 조합
     */
    private HashSet<Integer> getNumbers(ArrayList<Integer> numberList, int number, int depth, boolean[] visited) {
        HashSet<Integer> numbers = new HashSet<>();
        numbers.add(number);

        if (depth == numberList.size()) {
            return numbers;
        }

        for (int i = 0; i < numberList.size(); i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            numbers.addAll(getNumbers(numberList, number * 10 + numberList.get(i), depth + 1, visited));
            visited[i] = false;
        }

        return numbers;
    }

    /**
     * 소수인지 판별하는 메소드
     *
     * @param number 소수인지 판별할 숫자
     * @return 소수이면 true, 아니면 false
     */
    private boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}
