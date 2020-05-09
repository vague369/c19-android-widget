package com.android.c19widget.model

interface Repository {

    fun getData(callback: (RepoResult) -> Unit)
}