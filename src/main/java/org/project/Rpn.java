package org.project;

import java.util.List;

public interface Rpn {

    boolean isNumeric(String string);

    int getPriority(String string);

    List<String> toRpn(String[] values);

}
