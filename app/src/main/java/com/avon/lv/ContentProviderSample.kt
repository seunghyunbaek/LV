package com.avon.lv

import android.content.ContentUris
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.DateFormat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_content_provider_sample.*
import java.util.*

class ContentProviderSample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_sample)

        try {
            val cursor:Cursor = getImage()
            if (cursor.moveToFirst()) {
                // 1. 각 칼럼의 열 인덱스 얻기
                val idColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                val titleColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE)
                val dateTakenColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)

                // 2. 인덱스를 바탕으로 데이터를 Cursor로부터 얻기
                val id = cursor.getLong(idColNum)
                val title = cursor.getString(titleColNum)
                val dateTaken = cursor.getLong(dateTakenColNum)
                val imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                // 3. 데이터를 View로 설정
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = dateTaken
                val text = DateFormat.format("yyyy/MM/dd(E) kk:mm:ss", calendar).toString()
                textView_provider_sample.setText("촬영일시: " + text)
                imageView_provider_sample.setImageURI(imageUri)
            }
            cursor.close()
        } catch (e: SecurityException) {
            Toast.makeText(this, "스토리지에 접근 권한을 허가로 해주세요.（종료합니다)", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getImage(): Cursor {
        val contentResolver = contentResolver
        var queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        // 가져올 칼럼명
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATE_TAKEN)

        // 정렬
        val sortOrder = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"
        queryUri = queryUri.buildUpon().appendQueryParameter("limit", "1").build()

        return contentResolver.query(queryUri, projection, null, null, sortOrder)
    }
}
