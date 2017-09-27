package com.ssn.spring.annotation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by savos on 27.08.2017.
 */
@Service
//@Profile("starwars")
public class ObiWanKenobi implements Defender, InitializingBean {

    @Override
    public void protect() {
        System.out.println("May the Force be with you");
    }

    @PostConstruct
    public void init(){
        System.out.println("1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("2");
    }

    public void init2(){
        System.out.println("3");
    }

}
