/*
 * 프로그래머스 67257번. 수식 최대화
 * https://school.programmers.co.kr/learn/courses/30/lessons/67257
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.swap;

class Solution {

    private final char PLUS = '+';
    private final char MINUS = '-';
    private final char MULTIPLY = '*';

    /**
     * 연산자의 우선순위를 자유롭게 재정의하여 만들 수 있는 가장 큰 숫자를 반환하는 메소드
     *
     * @param expression 참가자에게 주어진 연산 수식이 담긴 문자열
     * @return 우승 시 받을 수 있는 가장 큰 상금 금액
     */
    public long solution(String expression) {
        // +, -, * 연산자의 우선순위를 재정의하여 만들 수 있는 경우는 6가지이며, 완전 탐색을 통해 모든 경우를 확인한다.
        long answer = Integer.MIN_VALUE;

        // 연산자 우선순위를 저장할 리스트
        ArrayList<ArrayList<Character>> operatorPriority = permutation(new ArrayList<>(List.of(PLUS, MINUS, MULTIPLY)), 0);

        // 연산자와 피연산자를 따로 분리하여 리스트에 저장
        ArrayList<Long> operandList = new ArrayList<>();
        ArrayList<Character> operatorList = new ArrayList<>();

        // 피연산자 저장
        for (long operand : Arrays.stream(expression.split("[+*-]")).mapToLong(Long::parseLong).toArray()) {
            operandList.add(operand);
        }

        // 연산자 저장
        for (char operator : expression.replaceAll("[0-9]+", "").toCharArray()) {
            operatorList.add(operator);
        }

        for (ArrayList<Character> priority : operatorPriority) {
            long result = calculate(operandList, operatorList, priority);
            answer = Math.max(answer, Math.abs(result));
        }

        return answer;
    }

    /**
     * 연산자 우선순위에 따라 연산을 수행하는 메소드
     *
     * @param operandList  피연산자가 담긴 리스트
     * @param operatorList 연산자가 담긴 리스트
     * @param priority     연산자 우선순위
     * @return 연산자 우선순위에 따라 연산을 수행한 결과값
     */
    private long calculate(ArrayList<Long> operandList, ArrayList<Character> operatorList, ArrayList<Character> priority) {
        ArrayList<Long> operand = new ArrayList<>(operandList);
        ArrayList<Character> operator = new ArrayList<>(operatorList);

        for (char op : priority) {
            for (int i = 0; i < operator.size(); i++) {
                if (operator.get(i) == op) {
                    long result = calculate(operand.remove(i), operand.remove(i), op);
                    operand.add(i, result);
                    operator.remove(i);
                    i--;
                }
            }
        }

        return operand.get(0);
    }

    /**
     * 두 피연산자와 연산자를 받아 연산을 수행하는 메소드
     *
     * @param a  피연산자1
     * @param b  피연산자2
     * @param op 연산자
     * @return 연산 결과값
     */
    private long calculate(long a, long b, char op) {
        if (op == PLUS) {
            return a + b;
        } else if (op == MINUS) {
            return a - b;
        } else {
            return a * b;
        }
    }

    /**
     * 연산자 우선순위의 모든 경우의 수를 구하는 메소드
     *
     * @param arr   연산자가 담긴 리스트
     * @param start 순열을 구할 때 사용하는 시작 인덱스
     * @return 연산자 우선순위의 모든 경우의 수
     */
    private ArrayList<ArrayList<Character>> permutation(ArrayList<Character> arr, int start) {
        ArrayList<ArrayList<Character>> result = new ArrayList<>();

        if (start == arr.size() - 1) {
            result.add(new ArrayList<>(arr));
            return result;
        }

        for (int i = start; i < arr.size(); i++) {
            swap(arr, start, i);
            result.addAll(permutation(arr, start + 1));
            swap(arr, start, i);
        }

        return result;
    }
}
