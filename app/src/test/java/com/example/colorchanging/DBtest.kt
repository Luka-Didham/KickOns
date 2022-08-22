import com.example.colorchanging.DatabseHelper
import com.example.colorchanging.DeckItem
import java.io.IOException
import org.junit.After
import org.junit.Test
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class DBtest {
    private lateinit var deckItem: DeckItem
    private lateinit var db: DatabseHelper

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TestDatabase::class.java).build()
        userDao = db.getUserDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user: User = TestUtil.createUser(3).apply {
            setName("george")
        }
        userDao.insert(user)
        val byName = userDao.findUsersByName("george")
        assertThat(byName.get(0), equalTo(user))
    }
}
