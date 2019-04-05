import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  
        ElementTest.class,
        ClusterTest.class,
        KMeanClusteringTest.class,
				})

public class AllTests {

}