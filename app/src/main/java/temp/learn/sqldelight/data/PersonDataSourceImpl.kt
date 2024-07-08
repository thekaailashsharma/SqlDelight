package temp.learn.sqldelight.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import temp.learn.sqldelight.AppDatabase
import temp.learn.sqldelight.Person

class PersonDataSourceImpl(
    db: AppDatabase
): PersonDataSource {

    private val queries = db.personEntityQueries

    override suspend fun getPersonById(id: Long): Person? {
        return withContext(Dispatchers.IO) {
            queries.getPersonById(id).executeAsOneOrNull()
        }
    }

    override fun getAllPersons(): Flow<List<Person>> {
        return queries.getAllPersons().asFlow().mapToList()
    }

    override suspend fun deletePersonById(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deletePersonById(id)
        }
    }

    override suspend fun insertPerson(firstName: String, lastName: String, id: Long?, age: Long?) {
        withContext(Dispatchers.IO) {
            queries.insertPerson(id, firstName, lastName, age, "")
        }
    }
}