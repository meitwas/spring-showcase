package com.ssn.spring.annotation;

/**
 * Created by savos on 27.08.2017.
 */
//@Service
//@Profile("terminator")
public class Terminator implements Defender {

    @Override
    public void protect() {
        System.out.println("I'll be back");
    }
}
