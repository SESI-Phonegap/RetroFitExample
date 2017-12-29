package android.chris.com.retrofittuto.utilskotlin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider

import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UtilPhotoCamera(private val context: Context) {

    internal val TAG = this.javaClass.simpleName

    private var photoPath: String? = null


    @Throws(IOException::class)
    fun takePhotoIntent(): Intent {
        val `in` = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        `in`.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        // Ensure that there's a camera activity to handle the intent
        if (`in`.resolveActivity(context.packageManager) != null) {
            // Create the File where the photo should go
            val photoFile = createImageFile()

            // Continue only if the File was successfully created
            if (photoFile != null) {
                `in`.putExtra(MediaStore.EXTRA_OUTPUT, if (Build.VERSION.SDK_INT >= 23) FileProvider.getUriForFile(context, "android.despacho.com.ofinicaerp", photoFile) else Uri.fromFile(photoFile))
            }
        }
        return `in`
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        photoPath = image.absolutePath
        return image
    }

    fun addToGallery() {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(photoPath!!)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        context.sendBroadcast(mediaScanIntent)
    }
}