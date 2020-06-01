package android.example.cyclesave

import android.app.Application

class GlobalVariables : Application() {
    companion object {
        var totalCost: Int = 0
    }
}