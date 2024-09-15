/*
 * 프로그래머스 42587번. 프로세스
 * https://school.programmers.co.kr/learn/courses/30/lessons/42587
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;

class Solution {

    /**
     * 입력받은 프로세스가 몇 번째로 실행되는지 계산하는 메소드
     *
     * @param priorities 현재 실행 대기 큐(Queue)에 있는 프로세스의 중요도가 순서대로 담긴 배열
     * @param location   몇 번째로 실행되는지 알고싶은 프로세스의 위치
     * @return location 프로세스가 몇 번째로 실행되는지
     */
    public int solution(int[] priorities, int location) {
        // 실행 대기 큐에 프로세스 정보를 담는다.
        ArrayDeque<Process> queue = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Process(i, priorities[i]));
        }

        // 우선순위를 높은 순서대로 정렬한다.
        priorities = Arrays.stream(priorities)
                .boxed().sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue).toArray();

        // 프로세스를 처리하며, location이 몇 번째로 처리되는지 계산한다.
        int sequence = 0;
        while (!queue.isEmpty()) {
            Process process = queue.remove();

            if (process.priority == priorities[sequence]) {
                sequence++;

                if (process.index == location) { // location이 처리되는 경우
                    return sequence;
                }

            } else {
                queue.add(process);
            }
        }

        return -1;
    }

    /**
     * 운영체제의 프로세스 정보를 담는 클래스
     */
    public class Process {
        int index;
        int priority;

        public Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
