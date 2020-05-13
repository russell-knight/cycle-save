package android.example.cyclesave

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {

    private val splashQuotes = listOf("Save time and money","Stay fit!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val textView = findViewById(R.id.splash_text) as TextView
        textView.text = splashQuotes.random()
    }
}
