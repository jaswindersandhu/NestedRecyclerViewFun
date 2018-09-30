package ca.nick.nestedrecyclerviewfun.utils

import android.content.Context
import android.content.res.Configuration

fun Context.isPortrait() =
    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT