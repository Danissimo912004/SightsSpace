package com.example.sightsspace
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var currentIndex = 0


    data class ArtItem(
        val imageResId: Int,
        val title: String,
        val country: String,
        val city: String
    )

    private val artList = listOf(
        ArtItem(R.drawable.art1, "Нотр-Дам-де-Пари", "Франция", "Париж"),
        ArtItem(R.drawable.art2, "Статуя Христа-Искупителя", "Бразилия", "Рио-де-Жанейро"),
        ArtItem(R.drawable.art3, "Московский Кремль", "Россия", "Москва")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.artImage)
        val titleView = findViewById<TextView>(R.id.artTitle)
        val countryView = findViewById<TextView>(R.id.artCountry)
        val cityView = findViewById<TextView>(R.id.artCity)
        val prevButton = findViewById<Button>(R.id.prevButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("index", 0)
        }

        fun updateContent() {
            val item = artList[currentIndex]
            imageView.setImageResource(item.imageResId)
            titleView.text = "Название: ${item.title}"
            countryView.text = "Страна: ${item.country}"
            cityView.text = "Город: ${item.city}"
        }


        updateContent()

        prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + artList.size) % artList.size
            updateContent()
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % artList.size
            updateContent()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("index", currentIndex)
    }
}
