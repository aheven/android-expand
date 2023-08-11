package heven.holt.expand.ktx

internal const val NO_GETTER = "Property does not have a getter"

internal fun noGetter(): Nothing = throw NotImplementedError(NO_GETTER)