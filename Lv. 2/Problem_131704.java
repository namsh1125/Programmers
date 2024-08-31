/*
 * 프로그래머스 131704번. 택배상자
 * https://school.programmers.co.kr/learn/courses/30/lessons/131704
 */

import java.util.*;

class Solution {

    /**
     * @param order 택배 기사님이 원하는 상자 순서를 나타내는 정수 배열
     * @return 영재가 실을 수 있는 최대 상자의 수
     */
    public int solution(int[] order) {
        Queue<Integer> container = new LinkedList<>(); // 메인 컨테이너 벨트
        Stack<Integer> subContainer = new Stack<>(); // 보조 컨테이너 벨트
        boolean[] isInContainer = new boolean[order.length + 1]; // 각 상자가 메인 컨테이너 벨트에 존재하는지 여부

        // 메인 컨테이너 벨트에 1번 상자부터 상자를 담는다.
        for (int i = 0; i < order.length; i++) {
            container.add(i + 1);
            isInContainer[i + 1] = true;
        }

        int answer = 0; // 영재가 실을 수 있는 최대 상자의 수
        for (int wantedBox : order) {
            if (!container.isEmpty() && container.peek() == wantedBox) { // 메인 컨테이너 벨트의 맨 앞 상자가 영재가 원하는 상자라면
                answer++; // 영재가 상자를 실었다.
                container.poll(); // 메인 컨테이너 벨트에서 상자를 빼낸다.
                isInContainer[wantedBox] = false; // 해당 상자가 메인 컨테이너 벨트에 존재하지 않음을 표시한다.

            } else { // 메인 컨테이너 벨트의 맨 앞 상자가 영재가 원하는 상자가 아니라면
                if (isInContainer[wantedBox]) { // 메인 컨테이너 벨트에 존재하는 경우
                    // 원하는 상자가 나올 때까지 상자를 보조 컨테이너 벨트로 옮긴다.
                    while (container.peek() != wantedBox) {
                        int box = container.poll();
                        subContainer.push(box);
                        isInContainer[box] = false;
                    }

                    // 원하는 상자를 실는다.
                    answer++;
                    container.poll();
                    isInContainer[wantedBox] = false;

                } else { // 보조 컨테이너 벨트에 상자가 존재하는 경우
                    if (subContainer.peek() == wantedBox) { // 원하는 상자가 보조 컨테이너 바로 위에 있는 경우
                        answer++;
                        subContainer.pop();
                        isInContainer[wantedBox] = false;

                    } else { // 원하는 상자가 보조 컨테이너 바로 위에 없는 경우
                        break;
                    }
                }
            }
        }

        return answer;
    }
}
