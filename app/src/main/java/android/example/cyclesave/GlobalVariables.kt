package android.example.cyclesave

import android.app.Application

class GlobalVariables : Application() {
    companion object {
        const val FULL_FARE = 4.50
        const val CONCES_FARE = 2.25
        var totalCost = 0.0
    }
}