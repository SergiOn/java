package com.itemsharing.itemservice.service.impl;

import com.itemsharing.itemservice.client.UserFeignClient;
import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.ItemRepository;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;
import com.itemsharing.itemservice.util.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);

    private ItemRepository itemRepository;
    private UserService userService;
    private UserFeignClient userFeignClient;

    public ItemServiceImpl(ItemRepository itemRepository, UserService userService, UserFeignClient userFeignClient) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public Item addItemByUser(Item item, String username) {
        Item localItem = itemRepository.findByName(item.getName());

        if (localItem != null) {
            LOG.info("Item with name {} already exists. Nothing will be done.", item.getName());
            return null;
        } else {
            Date today = new Date();
            item.setAddDate(today);

            User user = userService.findByUsername(username);
            item.setUser(user);
            Item newItem = itemRepository.save(item);

            return newItem;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByUsername(String username) {
        User user = userService.findByUsername(username);

        return itemRepository.findByUser(user);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        Item localItem = itemRepository.findById(item.getId()).orElseThrow(() -> new IOException("Item was not found"));

        localItem.setName(item.getName());
        localItem.setItemCondition(item.getItemCondition());
        localItem.setStatus(item.getStatus());
        localItem.setDescription(item.getDescription());

        return itemRepository.save(localItem);

//        if (localItem == null) {
//            throw new IOException("Item was not found");
//        } else {
//            localItem.setName(item.getName());
//            localItem.setItemCondition(item.getItemCondition());
//            localItem.setStatus(item.getStatus());
//            localItem.setDescription(item.getDescription());
//
//            return itemRepository.save(localItem);
//        }
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
//    @HystrixCommand(commandProperties = { @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "12000") })
//    @HystrixCommand(fallbackMethod = "buildFallbackUser")
    @HystrixCommand(fallbackMethod = "buildFallbackUser", threadPoolKey = "itemByUserThreadPool", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            //(requests per second at peak when the service is healthy * 99th percentile latency in seconds) + small amount of extra threads for overhead
            @HystrixProperty(name = "maxQueueSize", value = "10"),
    })
    public User getUserByUsername(String username) {
        randomlyRunLong();

        LOG.debug("ItemService.getUserByUsername Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        return userFeignClient.getUserByUsername(username);
//        return userService.findByUsername(username);
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomRun = rand.nextInt((3 - 1) + 1) + 1;
        if (randomRun == 3) sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private User buildFallbackUser(String username) {
        User user = new User();
        user.setId(123123L);
        user.setUsername("Temp user");

        return user;
    }

}
