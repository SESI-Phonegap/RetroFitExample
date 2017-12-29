package android.chris.com.retrofittuto.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UtilMoney {

    private static final String PAYMENT_NUMBER_FORMAT = "#,###,##0.00";
    private static final String STRING_EMPTY = "";

    public static double convertStringMoneyToDouble(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try {
            DecimalFormat format = new DecimalFormat(PAYMENT_NUMBER_FORMAT, new DecimalFormatSymbols(Locale.US));
            return format.parse(value).doubleValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }

    public static String convertDoubleToStringMoney(double value) {
        try {
            DecimalFormat format = new DecimalFormat(PAYMENT_NUMBER_FORMAT, new DecimalFormatSymbols(Locale.US));
            return format.format(value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return STRING_EMPTY;
        }
    }
}
