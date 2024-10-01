/*
 * 프로그래머스 17686번. [3차] 파일명 정렬
 * https://school.programmers.co.kr/learn/courses/30/lessons/17686
 */

import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        return Arrays.stream(files)
                .map(File::new)
                .sorted()
                .map(File::getFileName)
                .toArray(String[]::new);
    }

    /**
     * 파일명을 head, number, tail로 나누는 메소드
     *
     * @param file 파일명
     * @return 파일명을 head, number, tail로 나눈 배열
     */
    public String[] splitFileName(String file) {
        int numberStartIndex = file.length() - 1;
        int tailStartIndex = file.length();
        boolean flag = false; // number가 시작되었는지 여부

        // 파일명을 문자 단위로 읽어가며 head, number, tail로 나눈다.
        for (int i = 0; i < file.length(); i++) {
            char c = file.charAt(i);

            if (Character.isDigit(c)) {
                if (!flag) { // 처음으로 숫자가 나오면 number가 시작된 것
                    numberStartIndex = i;
                    flag = true;
                }
            } else {
                if (flag) { // 숫자가 끝나면 tail이 시작된 것
                    tailStartIndex = i;
                    break;
                }
            }
        }

        // number의 길이는 최대 5글자이므로, tail의 인덱스를 갱신한다.
        tailStartIndex = Math.min(tailStartIndex, numberStartIndex + 5);

        String head = file.substring(0, numberStartIndex);
        String number = file.substring(numberStartIndex, tailStartIndex);
        String tail = file.substring(tailStartIndex);

        return new String[] {head, number, tail};
    }

    public class File implements Comparable<File> {
        String fileName;
        String head;
        String number;
        String tail;

        public File(String fileName) {
            this.fileName = fileName;

            String[] strings = splitFileName(fileName);
            this.head = strings[0];
            this.number = strings[1];
            this.tail = strings[2];
        }

        public String getFileName() {
            return fileName;
        }

        @Override
        public int compareTo(File other) {
            if (this.head.equalsIgnoreCase(other.head)) {
                return Integer.parseInt(this.number) - Integer.parseInt(other.number);
            }
            return this.head.compareToIgnoreCase(other.head);
        }

    }
}
