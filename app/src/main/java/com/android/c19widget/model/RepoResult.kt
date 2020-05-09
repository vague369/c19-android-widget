package com.android.c19widget.model

sealed class RepoResult {
    class Success(val data: Pair<String, String>) : RepoResult()
    class Failure(val err: String) : RepoResult()
}