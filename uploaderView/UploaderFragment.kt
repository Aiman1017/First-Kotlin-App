package com.example.uploaderView

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.firstKotlinApp.R
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class UploaderFragment(activity: Activity) : Fragment(R.layout.fragment_upload){
    var resolver = activity.contentResolver
    lateinit var filePath: Uri
    lateinit var myImageView: ImageView
    lateinit var myUploadTextView: TextView
    lateinit var myUploadButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myImageView = view.findViewById<ImageView>(R.id.imageViewUploader)
        myUploadTextView = view.findViewById<TextView>(R.id.textViewUploader)
        myUploadButton = view.findViewById<Button>(R.id.buttonUploader)
        myImageView.setOnClickListener {
            Log.i("Upload", "Image clicked")
            var i = Intent()
            //Get the image file
            i.action = Intent.ACTION_GET_CONTENT
            i.type = "image/*"
            //Knowing where the request is coming from
            startActivityForResult(i, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){
            filePath = data.data!!

            var source = if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P){
                ImageDecoder.createSource(resolver, filePath)
                }else {
                    TODO("VERSION.SDK_INT < P")
                }

            var bitmap = ImageDecoder.decodeBitmap(source)
            myImageView.setImageBitmap(bitmap)
            myImageView.setBackgroundColor(Color.WHITE)
            myUploadTextView.text = "Tap to select other photos"
            myUploadTextView.setBackgroundColor(Color.DKGRAY)
            myUploadButton.visibility = View.VISIBLE
            myUploadButton.setOnClickListener{
                uploadToFirebase()
            }
        }
    }

    fun uploadToFirebase(){
        var imageFileName = UUID.randomUUID().toString()
        var storageRef = FirebaseStorage.getInstance().reference.child("/images/$imageFileName")
        storageRef.putFile(filePath)
            .addOnSuccessListener {
                myUploadTextView.text = "Uploaded, Tap to select another"
                myUploadTextView.setPadding(10, 0, 0, 0)
                myUploadTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 5f)
                Log.i("Image name", "${it.metadata?.path}")
            }
            .addOnFailureListener{
                myUploadTextView.text = "Upload failed"
            }
    }
}