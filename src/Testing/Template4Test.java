package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
//Do not use any classes in junit.framework or junit.extensions

/**
 * Tests for {@link Foo}.
 *
 * @author user@example.com (John Doe)
 */
public class Template4Test {
//Usually, tests with JUnit4 do not need to extend anything

    @Test
    public void thisAlwaysPasses() {

    }

    @Test
    @Ignore
    public void thisIsIgnored() {
    }
}