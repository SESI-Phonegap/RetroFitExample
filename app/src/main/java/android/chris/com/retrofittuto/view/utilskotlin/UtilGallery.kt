package android.chris.com.retrofittuto.utilskotlin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build

class UtilGallery(private val context: Context) {

    internal val TAG = this.javaClass.simpleName

    private lateinit var photoUri: Uri

    fun getChooserTitle(): String {
        return "Select Pictures"
    }

    fun getPath(): String? {

        val path: String?
        // SDK < API11
        if (Build.VERSION.SDK_INT < 11) {
            path = UtilFilePath.getRealPathFromURI_BelowAPI11(context, photoUri)
            // SDK >= 11 && SDK < 19
        } else if (Build.VERSION.SDK_INT < 19) {
            path = UtilFilePath.getRealPathFromURI_API11to18(context, photoUri)
        } else {
            // SDK >= 19 (Android 4.4)
            path = UtilFilePath.getPath(context, photoUri)
        }

        return path
    }

    /*  fun setPhotoUri(photoUri: Uri) {
          this.photoUri = photoUri
      } */

    fun openGalleryIntent(): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        return Intent.createChooser(intent, getChooserTitle())
    }

}