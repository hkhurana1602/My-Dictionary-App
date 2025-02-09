package com.example.dictionaryapp.presentation

import android.os.Parcel
import android.os.Parcelable
import com.example.dictionaryapp.domain.model.WordItem

data class MainState(
    val isLoading: Boolean = false,
    val searchWord: String = "",
    val wordItem: WordItem? = null,
)
