package inventory.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static Boolean isEmpty(String str) {
        if (str == null)
            return true;

        if ("".compareTo(str.trim()) == 0)
            return true;

        return false;
    }

    public static Boolean compare(String str1, String str2) {
        if (str1 == null && str2 == null)
            return true;

        if (str1 == null && str2 != null)
            return false;

        if (str1 != null && str2 == null)
            return false;

        if (str1.compareTo(str2) == 0)
            return true;
        else
            return false;
    }

    public static List<String> genQueryIdList(List<Long> idList) {
        List<String> idStr = new ArrayList<String>();

        if (idList.size() < 1000) {
            String str = "";
            for (Long id : idList) {
                str += id + ",";
            }
            str = str.substring(0, str.length() - 1);
            idStr.add(str);
            return idStr;
        } else {
            int counter = 1;
            String str = "";
            for (Long id : idList) {
                str += id + ",";
                counter++;
                if (counter >= 1000) {
                    str = str.substring(0, str.length() - 1);
                    idStr.add(str);
                    counter = 1;
                    str = "";
                }
            }

            if (!StringUtils.isEmpty(str)) {
                str = str.substring(0, str.length() - 1);
                idStr.add(str);
            }
            return idStr;
        }

    }

    public static List<String> genQueryStrList(List<String> codes) {
        List<String> idStr = new ArrayList<String>();

        if (codes.size() < 1000) {
            String str = "";
            for (String code : codes) {
                str += "'" + code + "',";
            }
            str = str.substring(0, str.length() - 1);
            idStr.add(str);
            return idStr;
        } else {
            int counter = 1;
            String str = "";
            for (String code : codes) {
                str += "'" + code + "',";
                counter++;
                if (counter >= 1000) {
                    str = str.substring(0, str.length() - 1);
                    idStr.add(str);
                    counter = 1;
                    str = "";
                }
            }

            if (!StringUtils.isEmpty(str)) {
                str = str.substring(0, str.length() - 1);
                idStr.add(str);
            }
            return idStr;
        }

    }

    public static List<String> splitKeyword(String keyword, String regex) {
        if (isEmpty(keyword))
            return null;

        List<String> list = new ArrayList<String>();
        if (regex == null) {
            list.add(keyword);
            return list;
        }

        try {
            String[] str = keyword.split(regex);
            if (str != null && str.length > 0) {
                for (String s : str)
                    list.add(s);
            } else
                list.add(keyword);
        } catch (Exception ex) {
            ex.printStackTrace();
            list.add(keyword);
        }

        return list;
    }

    public static boolean isChinese(String str) {
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(s[i]);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                    || ub == Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS) {
                return true;
            }
        }
        return false;
    }

    public static String formatNumber(BigDecimal number) {
        if (number == null)
            return "0.00";
        DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
        return df.format(number.setScale(2, RoundingMode.FLOOR));
    }


}
