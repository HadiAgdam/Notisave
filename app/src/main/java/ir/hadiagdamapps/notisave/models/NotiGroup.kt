package ir.hadiagdamapps.notisave.models

import android.graphics.drawable.Drawable

data class NotiGroup(
    val title: String,
    val icon: Drawable?,
    val count: Int
)