package android.chris.com.retrofittuto.utilskotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable

import java.io.File
import java.io.FileNotFoundException


class ImageLoader private constructor() {

    private var filePath: String? = null

    private var width = 128
    private var height = 128 //default

    fun getBitMap(): Bitmap {

        val file = File(filePath!!)
        if (!file.exists()) {
            throw FileNotFoundException()
        }

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        BitmapFactory.decodeFile(filePath, options)

        options.inSampleSize = calculateInSampleSize(options, width, height)

        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(filePath, options)
    }


    fun getImageDrawable(): Drawable {
        val file = File(filePath!!)
        if (!file.exists()) {
            throw FileNotFoundException()
        }
        return Drawable.createFromPath(filePath)
    }

    fun from(filePath: String): ImageLoader? {
        this.filePath = filePath
        return instance
    }

    fun requestSize(width: Int, height: Int): ImageLoader? {
        this.height = width
        this.width = height
        return instance
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    companion object {

        private var instance: ImageLoader? = null


        fun init(): ImageLoader? {
            if (instance == null) {
                synchronized(ImageLoader::class.java) {
                    if (instance == null) {
                        instance = ImageLoader()
                    }
                }
            }
            return instance
        }
    }
}