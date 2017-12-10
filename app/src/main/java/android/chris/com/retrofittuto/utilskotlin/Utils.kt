package android.chris.com.retrofittuto.utilskotlin

import android.app.Application
import android.app.DatePickerDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast

import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Collections
import java.util.Locale


object Utils {

    fun rotateBitmap(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    fun encodeImageBase64(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.URL_SAFE or Base64.NO_WRAP)
    }

    fun showDialogDate(context: Context, editText: EditText) {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val newDate = Calendar.getInstance()
            newDate.set(year, month, dayOfMonth)
            editText.setText(dateFormatter.format(newDate.time))
        }, year, month, day)
        datePickerDialog.show()
    }

    fun validarEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun compartirRedesSociales(context: Application) {
        val targetShareIntents = ArrayList<Intent>()
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        val pm = context.applicationContext.packageManager
        val resInfos = pm.queryIntentActivities(shareIntent, 0)
        if (!resInfos.isEmpty()) {
            //   System.out.println("Have package");
            for (resInfo in resInfos) {
                val packageName = resInfo.activityInfo.packageName
                Log.i("Package Name", packageName)

                /*         if (packageName.contains("com.twitter.android") || packageName.contains("com.facebook.katana")
                 || packageName.contains("com.whatsapp") || packageName.contains("com.google.android.apps.plus")
                 || packageName.contains("com.google.android.talk") || packageName.contains("com.slack")
                 || packageName.contains("com.google.android.gm") || packageName.contains("com.facebook.orca")
                 || packageName.contains("com.yahoo.mobile") || packageName.contains("com.skype.raider")
                 || packageName.contains("com.android.mms")|| packageName.contains("com.linkedin.android")
                 || packageName.contains("com.google.android.apps.messaging")) {*/
                if (packageName == "com.twitter.android" || packageName == "com.facebook.katana"
                        || packageName == "com.google.android.apps.plus" || packageName == "com.whatsapp"
                        || packageName == "com.android.mms" || packageName == "com.google.android.apps.messaging") {
                    val intent = Intent()

                    intent.component = ComponentName(packageName, resInfo.activityInfo.name)
                    intent.putExtra("AppName", resInfo.loadLabel(pm).toString())
                    intent.action = Intent.ACTION_SEND
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, "Tu App content text")
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Compartir")
                    intent.`package` = packageName
                    targetShareIntents.add(intent)
                }
            }
            if (!targetShareIntents.isEmpty()) {
                Collections.sort(targetShareIntents) { o1, o2 -> o1.getStringExtra("AppName").compareTo(o2.getStringExtra("AppName")) }
                val chooserIntent = Intent.createChooser(targetShareIntents.removeAt(0), "Select app to share")
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray())
                context.startActivity(chooserIntent)
            } else {
                Toast.makeText(context.applicationContext, "No app to share.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
