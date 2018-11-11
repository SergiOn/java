package com.packt.sb5be.searchapp.dataservice;

import com.packt.sb5be.searchapp.dataservice.dbmodel.orm.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataserviceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testBasicSearch() {
		Topic[] topics = this.restTemplate.getForObject("/topics?searchString=spring", Topic[].class);
		assertTrue(topics.length >= 2);
		for(int i=0; i<topics.length; i++){
			assertTrue(topics[i].getName() != null);
		}
	}

}
