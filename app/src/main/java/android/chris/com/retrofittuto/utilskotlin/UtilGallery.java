package android.chris.com.retrofittuto.utilskotlin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class UtilGallery {

    final String TAG = this.getClass().getSimpleName();
    private Context context;

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    private Uri photoUri;

    public UtilGallery(Context context) {
        this.context = context;
    }

    public Intent openGalleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        return Intent.createChooser(intent, getChooserTitle());
    }

    public String getChooserTitle() {
        return "Select Pictures";
    }

    public String getPath() {

        String path;
        // SDK < API11
        if (Build.VERSION.SDK_INT < 11) {
            path = UtilFilePath.getRealPathFromURI_BelowAPI11(context, photoUri);
        }

        // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19) {
            path = UtilFilePath.getRealPathFromURI_API11to18(context, photoUri);
        }
        // SDK >= 19 (Android 4.4)
        else {
            path = UtilFilePath.getPath(context, photoUri);
        }

        return path;
    }

}