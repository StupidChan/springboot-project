package com.chen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Api(description  = "第二个测试接口")
@RestController
public class TestController {

    @ApiOperation(value = "并行测试")
    @PostMapping("/asyncTest")
    public Map<String, Object> asyncTest() throws ExecutionException, InterruptedException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS") .format(new Date()));
        List<String> list = creatList(10);
        Integer num = creatInteger(10);

        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("num",num);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS") .format(new Date()));

        List<String> list2 = new ArrayList<>();
        Integer num2 = 0;
        CompletableFuture<List<String>> future1 = CompletableFuture.supplyAsync(() -> creatList(5));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> creatInteger(5));
        list2 = future1.get();
        num2 = future2.get();

        map.put("list2",list2);
        map.put("num2",num2);

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS") .format(new Date()));
        return map;
    }

    public List<String> creatList(int size){
        List<String> list = new ArrayList<>();
        for(int i=0; i<size; i++){
            list.add(i + " xx ");
        }
        try{
            Thread.sleep(4000);
        }catch (Exception e){

        }

        return list;
    }

    public Integer creatInteger(int size){
        int num = 0;
        num = size * size ;
        try{
            Thread.sleep(4000);
        }catch (Exception e){

        }
        return num;
    }
}
