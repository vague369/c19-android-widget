package com.android.c19widget.model

sealed class RepoResult
data class Success(val data: Pair<String, String>) : RepoResult()
data class Failure(val err: String) : RepoResult()
