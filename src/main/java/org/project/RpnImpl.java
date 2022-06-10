package org.project;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RpnImpl implements Rpn {

    @Override
    public boolean isNumeric(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public int getPriority(String s) {
        if (s.equals("*") || s.equals("/"))
            return 2;
        if (s.equals("+") || s.equals("-"))
            return 1;
        return 0;
    }

    @Override
    public List<String> toRpn(String[] values) {
        List<String> rpnExpression = new ArrayList<>();
        Deque<String> stack = new LinkedList<>();

        for (String s : values) {
            if (isNumeric(s)) {
                rpnExpression.add(s);
            } else {
                while (!stack.isEmpty() && getPriority(stack.getFirst()) >= getPriority(s))
                    rpnExpression.add(stack.poll());
                stack.addFirst(s);
            }
        }

        while (!stack.isEmpty()) {
            rpnExpression.add(stack.poll());
        }

        return rpnExpression;
    }
}
