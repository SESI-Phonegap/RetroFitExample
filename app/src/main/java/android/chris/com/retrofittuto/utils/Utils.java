package android.chris.com.retrofittuto.utils;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;


public class Utils {

    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static String encodeImageBase64(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.URL_SAFE | Base64.NO_WRAP);
    }

    public static void showDialogDate(Context context, final EditText editText) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                editText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public static boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static void compartirRedesSociales(Application context) {
        List<Intent> targetShareIntents = new ArrayList<>();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        PackageManager pm = context.getApplicationContext().getPackageManager();
        List<ResolveInfo> resInfos = pm.queryIntentActivities(shareIntent, 0);
        if (!resInfos.isEmpty()) {
            //   System.out.println("Have package");
            for (ResolveInfo resInfo : resInfos) {
                String packageName = resInfo.activityInfo.packageName;
                Log.i("Package Name", packageName);

/*         if (packageName.contains("com.twitter.android") || packageName.contains("com.facebook.katana")
                 || packageName.contains("com.whatsapp") || packageName.contains("com.google.android.apps.plus")
                 || packageName.contains("com.google.android.talk") || packageName.contains("com.slack")
                 || packageName.contains("com.google.android.gm") || packageName.contains("com.facebook.orca")
                 || packageName.contains("com.yahoo.mobile") || packageName.contains("com.skype.raider")
                 || packageName.contains("com.android.mms")|| packageName.contains("com.linkedin.android")
                 || packageName.contains("com.google.android.apps.messaging")) {*/
                if (packageName.contains("com.twitter.android") || packageName.contains("com.facebook.katana")
                        || packageName.contains("com.google.android.apps.plus") || packageName.contains("com.whatsapp")
                        || packageName.contains("com.android.mms") || packageName.contains("com.google.android.apps.messaging")) {
                    Intent intent = new Intent();

                    intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                    intent.putExtra("AppName", resInfo.loadLabel(pm).toString());
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Tu App content text");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Compartir");
                    intent.setPackage(packageName);
                    targetShareIntents.add(intent);
                }
            }
            if (!targetShareIntents.isEmpty()) {
                Collections.sort(targetShareIntents, new Comparator<Intent>() {
                    @Override
                    public int compare(Intent o1, Intent o2) {
                        return o1.getStringExtra("AppName").compareTo(o2.getStringExtra("AppName"));
                    }
                });
                Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), "Select app to share");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
                context.startActivity(chooserIntent);
            } else {
                Toast.makeText(context.getApplicationContext(), "No app to share.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
