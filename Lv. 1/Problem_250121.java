/*
 * 프로그래머스 250121번. [PCCE 기출문제] 10번 / 데이터 분석
 * https://school.programmers.co.kr/learn/courses/30/lessons/250121
 */

import java.util.ArrayList;

class Solution {

    /**
     * 데이터 분석
     *
     * @param data   정렬한 데이터들이 담긴 이차원 정수 리스트
     * @param ext    어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열
     * @param valExt 뽑아낼 정보의 기준값을 나타내는 정수
     * @param sortBy 정보를 정렬할 기준이 되는 문자열
     * @return data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후, sort_by에 해당하는 값을 기준으로 오름차순으로 정렬한 이차원 정수 리스트
     */
    public int[][] solution(int[][] data, String ext, int valExt, String sortBy) {
        // Data 전처리 (ext 값이 val_ext보다 작은 데이터만 뽑기)
        ArrayList<Data> dataList = new ArrayList<>();
        for (int[] d : data) {
            if (ext.equals("code") && d[0] >= valExt) continue;
            if (ext.equals("date") && d[1] >= valExt) continue;
            if (ext.equals("maximum") && d[2] >= valExt) continue;
            if (ext.equals("remain") && d[3] >= valExt) continue;

            dataList.add(new Data(d[0], d[1], d[2], d[3]));
        }

        // Data 정렬 (sort_by에 해당하는 값을 기준으로 오름차순으로 정렬)
        dataList.sort((o1, o2) -> {
            return switch (sortBy) {
                case "code" -> Integer.compare(o1.getCode(), o2.getCode());
                case "date" -> Integer.compare(o1.getDate(), o2.getDate());
                case "maximum" -> Integer.compare(o1.getMaximum(), o2.getMaximum());
                case "remain" -> Integer.compare(o1.getRemain(), o2.getRemain());
                default -> 0;
            };
        });

        // Data 이차원 정수 리스트로 변환
        return dataList.stream()
                .map(d -> new int[]{d.getCode(), d.getDate(), d.getMaximum(), d.getRemain()})
                .toArray(int[][]::new);
    }
}

class Data {
    private int code; // 코드 번호 (1 ≤ code ≤ 100,000)
    private int date; // 제조일 (yyyymmdd 형태, 20000101 ≤ date ≤ 29991231)
    private int maximum; // 최대값 (1 ≤ maximum ≤ 10,000)
    private int remain; // 현재 수량 (1 ≤ remain ≤ maximum)

    public Data(int code, int date, int maximum, int remain) {
        this.code = code;
        this.date = date;
        this.maximum = maximum;
        this.remain = remain;
    }

    public int getCode() {
        return code;
    }

    public int getDate() {
        return date;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getRemain() {
        return remain;
    }

}
