package io.malachai.finance;

import io.malachai.finance.presentation.OverviewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FinanceNewsCrawlerApplicationTests {

	@Autowired
	OverviewController overviewController;

	@Test
	void contextLoads() {
	}

	@Test
	void overviewPageRequest() {
		// given
		OverviewController sut = overviewController;
		Model modelStub = new ConcurrentModel();

		// when
		String result = sut.overview(modelStub);

		// then
		assertThat(result)
				.isEqualTo("overview/index");
		assertThat(modelStub.getAttribute("histories"))
				.asList()
				.isEmpty();
	}

}
