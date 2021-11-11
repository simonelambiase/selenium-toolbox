package utils;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileUtilsTest {

    @Test
    public void testFileReading() {
        FileUtils.copyPackagedResources();
    }


}