package com.tiantiannews.utils;

import com.tiantiannews.data.bean.City;

import java.util.Comparator;

public class PinyinComparator implements Comparator<City> {
    @Override
    public int compare(City lhs, City rhs) {
        if (lhs.letter.equals("@")
                || rhs.letter.equals("#")) {
            return -1;
        } else if (lhs.letter.equals("#")
                || rhs.letter.equals("@")) {
            return 1;
        } else {
            return lhs.letter.compareTo(rhs.letter);
        }
    }
}
