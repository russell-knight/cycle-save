package android.example.cyclesave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// import FloatingButton here?

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onPause() {
        super.onPause()

    }
}
