package android.chris.com.retrofittuto.utils;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v4.content.FileProvider;
        import java.io.File;
        import java.io.IOException;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Locale;

public class UtilPhotoCamera {

    final String TAG = this.getClass().getSimpleName();

    private String photoPath;

    public String getPhotoPath() {
        return photoPath;
    }

    private Context context;
    public UtilPhotoCamera(Context context){
        this.context = context;
    }

    public Intent takePhotoIntent() throws IOException {
        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        in.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // Ensure that there's a camera activity to handle the intent
        if (in.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = createImageFile();

            // Continue only if the File was successfully created
            if (photoFile != null) {
                in.putExtra(MediaStore.EXTRA_OUTPUT, (Build.VERSION.SDK_INT >= 23) ? FileProvider.getUriForFile(context, "android.despacho.com.ofinicaerp",photoFile) : Uri.fromFile(photoFile));
            }
        }
        return in;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        photoPath = image.getAbsolutePath();
        return image;
    }

    public void addToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }
}