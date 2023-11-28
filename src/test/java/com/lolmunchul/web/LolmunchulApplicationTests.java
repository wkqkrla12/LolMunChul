package com.lolmunchul.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class LolmunchulApplicationTests {


    public int[] sum(int[] arr){

        int[] answer = new int[arr.length];

        answer[0] = arr[0];

        for(int i = 1; i<arr.length; i++){
            answer[i] = answer[i-1] + arr[i];
        }
        return answer;
    }

    @Test
    void contextLoads() {

        int[] arr = {1,1,1,1,2,6,1,2};



        System.out.println(Arrays.toString(sum(arr)));
    }

}
