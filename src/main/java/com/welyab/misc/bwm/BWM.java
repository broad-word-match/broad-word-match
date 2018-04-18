package com.welyab.misc.bwm;

import java.util.List;
import java.util.Locale;

import com.welyab.misc.bwm.internal.BroadWordMatch;

public class BWM {

    public static List<WordInfo> find(String word) {
	return find(word, Locale.getDefault());
    }

    public static List<WordInfo> find(String word, Locale locale) {
	return BroadWordMatch.find(word, locale);
    }
}
