package com.welyab.misc.bwm.dictionary;

import java.util.List;
import java.util.Locale;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.welyab.misc.bwm.dictionary.spi.Dictionary;

public class Dictionaries {

    private static boolean dictionariesLoaded = false;
    private static List<Dictionary> availableDictionaries;

    public static List<Dictionary> getDictionaries(Locale locale) {
	if (locale == null) {
	    throw new NullPointerException("locale");
	}

	return getAvailableDictionaries()
		.stream()
		.filter(d -> isLocalMatch(d.getLocale(), locale))
		.collect(Collectors.toList());
    }

    private static boolean isLocalMatch(Locale dictionaryLocale, Locale requiredLocale) {
	if (StringUtils.isBlank(requiredLocale.getCountry())) {
	    return dictionaryLocale.getLanguage().equals(requiredLocale.getLanguage());
	}

	return dictionaryLocale.getLanguage().equals(requiredLocale.getLanguage())
		&& dictionaryLocale.getCountry().equals(requiredLocale.getCountry());
    }

    private static synchronized List<Dictionary> getAvailableDictionaries() {
	if (dictionariesLoaded) {
	    return availableDictionaries;
	}
	availableDictionaries = ServiceLoader
		.load(Dictionary.class)
		.stream()
		.map(Provider::get)
		.collect(Collectors.toList());
	return availableDictionaries;
    }
}
