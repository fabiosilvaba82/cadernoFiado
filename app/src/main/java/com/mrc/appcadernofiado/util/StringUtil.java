package com.mrc.appcadernofiado.util;

import java.text.Normalizer;

public class StringUtil {
    public static String unaccent(String src) {
        return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll(" [^\\p{ASCII}]", "");
    }
}
