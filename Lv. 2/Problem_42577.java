/*
 * 프로그래머스 42577번. 전화번호 목록
 * https://school.programmers.co.kr/learn/courses/30/lessons/42577
 */

import java.util.Arrays;

class Solution {

    /**
     * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 여부를 반환하는 메소드
     *
     * @param phone_book 전화번호부에 적힌 전화번호를 담은 배열
     * @return 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 여부
     */
    public boolean solution(String[] phone_book) {
        // 전화번호부에 적힌 전화번호를 오름차순으로 정렬
        // (정렬을 하면, 접두어인 경우가 연이어 위치하게 되므로, 이를 찾기 쉬워짐)
        Arrays.sort(phone_book);

        // 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}
