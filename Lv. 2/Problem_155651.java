/*
 * 프로그래머스 155651번. 호텔 대실
 * https://school.programmers.co.kr/learn/courses/30/lessons/155651
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {

    /**
     * 예약 시간이 문자열로 주어질 때, 필요한 최소 객실의 수를 반환하는 함수
     *
     * @param bookTimes 예약 시각이 문자열 형태로 담긴 2차원 배열
     * @return 필요한 최소 객실의 수
     */
    public int solution(String[][] bookTimes) {
        // 입력받은 book_time을 Reservation 객체로 변환
        PriorityQueue<Reservation> reservations = new PriorityQueue<>();

        for (String[] bookTime : bookTimes) {
            reservations.add(new Reservation(bookTime[0], bookTime[1]));
        }

        // 필요한 최소 객실의 수를 구한다
        // 한 번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님이 사용
        ArrayList<Reservation> rooms = new ArrayList<>();
        int answer = 0;

        while (!reservations.isEmpty()) {
            Reservation reservation = reservations.remove();

            // 현재 예약 시간에 사용 가능한 객실이 있는지 확인
            boolean available = false; // 사용 가능한 객실이 있는지 여부
            for (Reservation room : rooms) {
                if (room.end + 10 <= reservation.start) {
                    room.end = reservation.end;
                    available = true;
                    break;
                }
            }

            // 사용 가능한 객실이 없다면 새로운 객실을 추가
            if (!available) {
                rooms.add(reservation);
                answer++;
            }
        }

        return answer;
    }

    public class Reservation implements Comparable<Reservation> {
        int start;
        int end;

        /**
         * HH:MM 형식의 문자열을 받아서 Reservation 객체를 생성하는 생성자
         *
         * @param start 대실 시작 시각
         * @param end   대실 종료 시간
         */
        public Reservation(String start, String end) {
            this.start = Integer.parseInt(start.split(":")[0]) * 60 + Integer.parseInt(start.split(":")[1]);
            this.end = Integer.parseInt(end.split(":")[0]) * 60 + Integer.parseInt(end.split(":")[1]);
        }

        @Override
        public int compareTo(Reservation o) {
            return this.start - o.start;
        }

    }
}
