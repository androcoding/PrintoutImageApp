package com.example.printoutimageapp

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.print.PrintHelper


class MainActivity : AppCompatActivity() {
    lateinit var viewImage:ImageView
    lateinit var selectedImageView: Button
    lateinit var printImage:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewImage=findViewById(R.id.imageView)
        selectedImageView=findViewById(R.id.button_upload)
        printImage=findViewById(R.id.button_print)

        selectedImageView.setOnClickListener {
             val images= Intent(Intent.ACTION_PICK)
            images.type="image/*"
            startActivityForResult(images, 0)
        }

        printImage.setOnClickListener {
            val photoPrinter = PrintHelper(this)
            photoPrinter.scaleMode=PrintHelper.SCALE_MODE_FIT
            val bitmap = (viewImage.drawable as BitmapDrawable).bitmap
            photoPrinter.printBitmap("test print", bitmap)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 0){
           viewImage.setImageURI(data!!.data)

        }
    }

}