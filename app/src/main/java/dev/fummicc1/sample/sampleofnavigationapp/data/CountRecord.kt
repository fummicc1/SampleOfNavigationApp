package dev.fummicc1.sample.sampleofnavigationapp.data

import android.os.Parcel
import java.util.*
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountRecord(var amount: Int = 0, var time: Int = 10, var date: Date = Date()) : Parcelable