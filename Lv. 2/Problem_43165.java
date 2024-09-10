/*
 * 프로그래머스 43165번. 타겟 넘버
 * https://school.programmers.co.kr/learn/courses/30/lessons/43165
 */

class Solution {

    /**
     * 주어진 숫자로 타겟 넘버를 만드는 방법의 수를 구하는 메소드
     *
     * @param numbers 사용할 수 있는 숫자가 담긴 배열
     * @param target  타겟 넘버
     * @return 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수
     */
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    /**
     * DFS로 타겟 넘버를 만드는 방법의 수를 구하는 메소드
     *
     * @param numbers 사용할 수 있는 숫자가 담긴 배열
     * @param target  타겟 넘버
     * @param index   현재 사용할 숫자의 인덱스
     * @param sum     현재까지 더해진 숫자의 합
     * @return 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수
     */
    private int dfs(int[] numbers, int target, int index, int sum) {
        if (index == numbers.length) {
            return sum == target ? 1 : 0;
        }

        return dfs(numbers, target, index + 1, sum + numbers[index])
                + dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}
