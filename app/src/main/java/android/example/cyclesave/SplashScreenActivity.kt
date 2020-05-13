package android.example.cyclesave

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {

    private val splashQuotes = listOf(
        "In total, riding your bike accounts for about 21g of CO2 emissions per kilometer, less than 10x that of a car!",
        "Cycling can help to protect you from serious diseases such as stroke, heart attack, some cancers, depression, diabetes, obesity and arthritis.",
        "Steady cycling burns about 1,200 kilojoules (about 300 calories) per hour.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val textView = findViewById<TextView>(R.id.splash_text)
        textView.text = splashQuotes.random()
    }
}
