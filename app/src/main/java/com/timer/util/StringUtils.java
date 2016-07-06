package com.timer.util;

/**
 * Created by ccc on 16/7/6.
 */
public class StringUtils {

    /**
     * 根据数值获取名称
     * @param repeat 重复
     * @return
     */
    public static String encodeRepeat (String repeat) {
        return getRepeatByNumOrName(repeat, true);
    }

    /**
     * 根据名称获取数值
     * @param repeat 重复
     * @return
     */
    public static String decodeRepeat (String repeat) {
        return getRepeatByNumOrName(repeat, false);
    }

    // true根据数值找名称,false根据名称找数值
    private static String getRepeatByNumOrName (String repeat, boolean flag) {
        // 0,1,2,3,4,5,6
        // 周一 周二 周三 周四 周五 周六 周日 每天
        String all = "0,1,2,3,4,5,6";
        String[] defWeeks = new String[] {"周一", "周二", "周三", "周四", "周五", "周六", "周日", "每天"};
        String result = null;
        String allWeek1 = null;
        String allWeek2 = null;
        String replaceAll1 = null;
        String replaceAll2 = null;
        String[] weeksTemp1 = null;
        String[] weeksTemp2 = null;
        /*if (flag) {
            if (all.equals(repeat)) {
                result = defWeeks[7];
            } else {
                result = repeat.replaceAll(",", " ");
                for (int i = 0; i < defWeeks.length - 1; i ++) {
                    result = result.replace(String.valueOf(i), defWeeks[i]);
                }
            }
        } else {
            if (defWeeks[7].equals(repeat)) {
                result = all;
            } else {
                result = repeat.replaceAll(" ", ",");
                for (int i = 0; i < defWeeks.length - 1; i ++) {
                    result = result.replace(defWeeks[i], String.valueOf(i));
                }
            }
        }*/
        if (flag) {
            allWeek1 = all;
            allWeek2 = defWeeks[7];
            replaceAll1 = ",";
            replaceAll2 = " ";
            weeksTemp1 = all.split(",");
            weeksTemp2 = defWeeks;
        } else {
            allWeek1 = defWeeks[7];
            allWeek2 = all;
            replaceAll1 = " ";
            replaceAll2 = ",";
            weeksTemp1 = defWeeks;
            weeksTemp2 = all.split(",");
        }
        if (allWeek1.equals(repeat)) {
            result = allWeek2;
        } else {
            result = repeat.replaceAll(replaceAll1, replaceAll2);
            for (int i = 0; i < defWeeks.length - 1; i ++) {
                result = result.replace(weeksTemp1[i], weeksTemp2[i]);
            }
        }
        return result;
    }

    /**
     * 根据数值获取名称
     * @param operation 操作
     * @return
     */
    public static String encodeOperation (String operation) {
        return getOperationByNumOrName(operation, true);
    }

    /**
     * 根据名称获取数值
     * @param operation 操作
     * @return
     */
    public static String decodeOperation (String operation) {
        return getOperationByNumOrName(operation, false);
    }

    // true根据数值找名称,false根据名称找数值
    private static String getOperationByNumOrName (String operation, boolean flag) {
        // 0 1 2 3
        // 静音 铃声 振动 铃声振动
        String[] defOperationCode = new String[] {"0", "1", "2", "3"};
        String[] defOperationName = new String[] {"静音", "铃声", "振动", "铃声振动"};
        String[] defOperationTemp1 = defOperationCode;
        String[] defOperationTemp2 = defOperationName;
        String result = null;
        if (!flag) {
            defOperationTemp1 = defOperationName;
            defOperationTemp2 = defOperationCode;
        }
        for (int i = 0; i < defOperationTemp1.length; i ++) {
            String operationTemp1 = defOperationTemp1[i];
            if (operationTemp1.equals(operation)) {
                result = defOperationTemp2[i];
                break;
            }
        }
        return result;
    }
}
