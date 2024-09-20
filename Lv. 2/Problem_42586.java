/*
 * 프로그래머스 42586번. 기능개발
 * https://school.programmers.co.kr/learn/courses/30/lessons/42586
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {

    /**
     * 각 배포마다 몇 개의 기능이 배포되는지 반환하는 메소드
     *
     * @param progresses 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열
     * @param speeds     각 작업의 개발 속도가 적힌 정수 배열
     * @return 각 배포마다 몇 개의 기능이 배포되는지
     */
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int day = 0;
        int count = 0; // 하루에 배포되는 기능의 수

        // 반복문을 통해 우선순위 큐에 작업을 넣어준다.
        PriorityQueue<Job> queue = new PriorityQueue<>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add(new Job(i, progresses[i], speeds[i]));
        }

        // 우선순위 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Job job = queue.remove();
            int progress = job.progress + job.speed * day; // 현재 진행도

            if (progress < 100) {
                if (count > 0) {
                    answer.add(count); // 현재 진행도가 100% 미만이면 이전까지의 배포 가능한 기능의 수를 추가해준다.
                    count = 0; // 배포 가능한 기능의 수를 초기화한다.
                }

                day += (int) Math.ceil((100.0 - progress) / job.speed);
            }
            count++; // 현재 진행도가 100% 이상이면 배포 가능한 기능의 수를 증가시킨다.
        }
        answer.add(count);

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    public class Job implements Comparable<Job> {
        int priority;
        int progress;
        int speed;

        public Job(int priority, int progress, int speed) {
            this.priority = priority;
            this.progress = progress;
            this.speed = speed;
        }

        @Override
        public int compareTo(Job o) {
            return this.priority - o.priority;
        }
    }

}
