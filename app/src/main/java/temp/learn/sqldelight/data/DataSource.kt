package temp.learn.sqldelight.data

import kotlinx.coroutines.flow.Flow
import temp.learn.sqldelight.Person

interface PersonDataSource {

    suspend fun getPersonById(id: Long): Person?

    fun getAllPersons(): Flow<List<Person>>

    suspend fun deletePersonById(id: Long)

    suspend fun insertPerson(firstName: String, lastName: String, id: Long? = null, age: Long? = null)
}