package com.jge.newsstack.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Article (val author:String,
               val title:String,
               val description:String,
               val url:String,
               val source:String,
               val image:String,
               val category:String,
               val language:String,
               val country:String,
               @SerializedName("published_at") val publishedAt:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(source)
        parcel.writeString(image)
        parcel.writeString(category)
        parcel.writeString(language)
        parcel.writeString(country)
        parcel.writeString(publishedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}