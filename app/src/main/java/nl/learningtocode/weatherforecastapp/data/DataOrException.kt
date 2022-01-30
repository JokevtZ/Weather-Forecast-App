package nl.learningtocode.weatherforecastapp.data


//Wrapper class to handle error handling
class DataOrException <T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var e : E? = null
    )