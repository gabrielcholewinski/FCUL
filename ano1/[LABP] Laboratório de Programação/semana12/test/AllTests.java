import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({   
    UrlTest.class,
    OrganizacaoTest.class,
    BidirectionalHashMapTest.class,
    ShortenerTest.class
})

public class AllTests {

}
