package com.urise.webapp.util;

import com.urise.webapp.model.Position;

public class HTMLUtil {
    public static String formatDates(Position position) {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
