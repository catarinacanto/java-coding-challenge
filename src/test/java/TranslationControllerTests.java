import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class TranslationControllerTests {

	String testId;

	@Before
	public void setUp() throws Exception {
		String id = UUID.randomUUID().toString();
		testId = id.substring(0, 7) + id.substring(9,12);
	}

	@Test
	public void testIds() {
		String id = UUID.randomUUID().toString();
		String randomUid = id.substring(0, 7) + id.substring(9,12);

		for (int i = 0; i < 50; i++){
			Assert.assertNotEquals(testId, randomUid);
		}
	}
}
