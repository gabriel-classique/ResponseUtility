/*-----------------------------------------------------------------------------------------------------*/

typealias RootError = Error

sealed interface Response<out D, out E: RootError> {
    data class Success<out D, out E: RootError> (val data: D): Response<D, E>
    data class Failure<out D, out E: RootError> (val error: E): Response<D, E>
}

/*-----------------------------------------------------------------------------------------------------*/

//Single Error Interface
sealed interface Error{
    data class Connection(val message: String): Error
    data object Authentication: Error
    data object NotFound: Error
    data object InvalidCredentials: Error
    data object UserAlreadyExists: Error
}


// OR Seperated Error Enums
sealed interface Error

enum class ApiError: Error {
    QUERY_TOO_SHORT,
    NOT_FOUND,
    CONNECTION_ERROR,
}


/*--------------------------------  USAGE  ---------------------------------------------------------------------*/

// fun get(query): Response<List<ApiDTO>, ApiError>

val apiResult = repository.get(query)

when(apiResult){
    is Response.Success -> { 
	// do something with apiResult.data
    }
    
    is Response.Failure -> {
        when(apiResult.error){
            QUERY_TOO_SHORT ->
            NOT_FOUND ->
            CONNECTION_ERROR ->
	}
    }
}
