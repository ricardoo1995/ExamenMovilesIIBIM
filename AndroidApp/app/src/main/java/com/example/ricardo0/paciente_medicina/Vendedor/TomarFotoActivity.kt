package com.example.ricardo0.paciente_medicina.Vendedor

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.ricardo0.paciente_medicina.R
import kotlinx.android.synthetic.main.activity_tomar_foto.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TomarFotoActivity : AppCompatActivity() {
    var fileName = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomar_foto)
        boton_camara.setOnClickListener { view: View ->
            takePicture()
            var toast = Toast.makeText(this,fileName, Toast.LENGTH_LONG)
            toast.show()

        }

    }

    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File

            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile))
                startActivityForResult(takePictureIntent, TOMAR_FOTO_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == TOMAR_FOTO_REQUEST && resultCode == Activity.RESULT_OK) {

            loadImageFromFile(this.fileName)
            Log.i("file name", fileName)
        }
    }

    fun loadImageFromFile(file: String) {

        val view = this.findViewById(R.id.imagen_view) as ImageView
        view.setVisibility(View.VISIBLE)


        val targetW = view.getWidth()
        val targetH = view.getHeight()

        // Get the dimensions of the bitmap
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file, bmOptions)
        val photoW = bmOptions.outWidth
        val photoH = bmOptions.outHeight

        // Determine how much to scale down the image
        val scaleFactor = Math.min(photoW / targetW, photoH / targetH)

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor
        bmOptions.inPurgeable = true

        val bitmap = BitmapFactory.decodeFile(file, bmOptions)
        view.setImageBitmap(bitmap)
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        //val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        fileName = image.absolutePath
        return image
    }

    companion object {
        val TOMAR_FOTO_REQUEST = 1
    }
}
