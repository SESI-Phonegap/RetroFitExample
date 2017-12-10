package android.chris.com.retrofittuto.utilskotlin

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object UtilMoney {

    private val PAYMENT_NUMBER_FORMAT = "#,###,##0.00"

    fun convertStringMoneyToDouble(value: String?): Double {
        if (value == null || value.trim { it <= ' ' }.isEmpty()) {
            return 0.0
        }
        val format = DecimalFormat(PAYMENT_NUMBER_FORMAT, DecimalFormatSymbols(Locale.US))
        return format.parse(value).toDouble()
    }

    fun convertDoubleToStringMoney(value: Double): String {
        val format = DecimalFormat(PAYMENT_NUMBER_FORMAT, DecimalFormatSymbols(Locale.US))
        return format.format(value)
    }
}
