/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package lang;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.ResourceBundle;

public class loadLang {
    private Locale locale;
    private ResourceBundle bundle;

    private void load(String lang){
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("lang.MyLocale",locale);
    }
}
