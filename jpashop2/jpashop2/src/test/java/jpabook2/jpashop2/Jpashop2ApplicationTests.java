package jpabook2.jpashop2;

import jpabook2.jpashop2.Domain.QOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Jpashop2ApplicationTests {

	@Test
	void contextLoads() {
		QOrder qOrder = QOrder.order;
	}

}
