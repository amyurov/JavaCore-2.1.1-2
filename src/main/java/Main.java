import org.project.RpnImpl;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RpnImpl rpn = new RpnImpl();
        System.out.println("Введите математическое выражение:");
        String input = scanner.nextLine();
        String[] expression = input.split(" ");

        List<String> rpnExpression = rpn.toRpn(expression);

        System.out.println("Обратная польская запись:");
        for (String s : rpnExpression) {
            System.out.print(s + " ");
        }
    }

}
