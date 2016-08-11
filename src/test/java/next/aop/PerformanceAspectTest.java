package next.aop;

import javax.annotation.Resource;

import next.config.AppConfig;
import next.service.QnaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PerformanceAspectTest {

	@Resource
	private QnaService qnaService;

	@Test
	public void aop() {
		qnaService.findById(1);
	}

}
