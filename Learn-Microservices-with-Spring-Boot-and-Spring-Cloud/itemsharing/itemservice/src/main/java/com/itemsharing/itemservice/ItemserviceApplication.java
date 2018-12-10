package com.itemsharing.itemservice;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ItemserviceApplication implements CommandLineRunner {

	private ItemService itemService;
	private UserService userService;

	public ItemserviceApplication(ItemService itemService, UserService userService) {
		this.itemService = itemService;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ItemserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = userService.findByUsername("SerhiiOn");

		Item item1 = new Item();
		item1.setName("Item1");
		item1.setItemCondition("New");
		item1.setStatus("Active");
		item1.setAddDate(new Date());
		item1.setDescription("This is an item description.");
		item1.setUser(user);

		itemService.addItemByUser(item1, user.getUsername());

		Item item2 = new Item();
		item2.setName("Item2");
		item2.setItemCondition("Used");
		item2.setStatus("Inactive");
		item2.setAddDate(new Date());
		item2.setDescription("This is an item description for item2.");
		item2.setUser(user);

		itemService.addItemByUser(item2, user.getUsername());
	}
}
