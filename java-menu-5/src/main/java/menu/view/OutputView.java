package menu.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.model.Coach;
import menu.model.Week;

public class OutputView {

    public void printStart() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printResult(List<Coach> coaches, List<String> selectCategories) {
        System.out.println("\n메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | "
                + String.join(" | ", Arrays.stream(Week.values()).map(i -> i.getText()).collect(Collectors.toList()))
                + " ]");
        System.out.println("[ 카테고리 | " + String.join(" | ", selectCategories) + " ]");
        for (Coach coach : coaches) {
            System.out.println("[ " + coach.getName() + " | " + String.join(" | ", coach.getSelectMenu()) + " ]");
        }
        System.out.println("\n추천을 완료했습니다.");
    }
}
