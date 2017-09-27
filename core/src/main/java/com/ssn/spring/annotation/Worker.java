package com.ssn.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by savos on 27.08.2017.
 */

@Service
public class Worker {

    @Autowired(required = false)
    Defender defender;

    public void work() {
        defender.protect();
    }

}
