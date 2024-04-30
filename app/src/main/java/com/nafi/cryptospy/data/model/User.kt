package com.nafi.cryptospy.data.model

import com.google.firebase.auth.FirebaseUser

data class User(
    val photoUrl: String,
    val fullName: String,
    val email: String,
)

fun FirebaseUser?.toUser() =
    this?.let {
        User(
            photoUrl = this.photoUrl.toString(),
            fullName = this.displayName.orEmpty(),
            email = this.email.orEmpty(),
        )
    }
