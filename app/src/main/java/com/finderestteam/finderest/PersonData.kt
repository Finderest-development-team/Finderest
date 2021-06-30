package com.finderestteam.finderest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonData(var uid: String? = null,
    var userName: String? = null,
    var userMail: String? = null,
    var userPassword: String? = null,
    var profileImageUrl: String? = null,
    var userListOfInterests: String? = null): Parcelable{}
