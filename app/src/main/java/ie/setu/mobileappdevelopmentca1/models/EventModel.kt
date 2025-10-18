package ie.setu.mobileappdevelopmentca1.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventModel (
    var title: String = "",
    var description: String = ""
) : Parcelable