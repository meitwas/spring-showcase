package com.ssn.spring.xml;

import java.util.List;

public class HomerSimpson {

    private String phrase = "Doh";

    private Long beerAmount;

    private List<String> children;


    public HomerSimpson() {
    }

    public HomerSimpson(Long beerAmount) {
        this.beerAmount = beerAmount;
    }

    private HomerSimpson(String phrase) {
        this.phrase = phrase;
    }

    public static HomerSimpson createInstance(){
        return new HomerSimpson("Every time I learn something new, it pushes some old stuff out of my brain");
    }

    public BartSimpson born() {
        return new BartSimpson();
    }

    public void say() {
        System.out.println(phrase);
    }

    public Long getBeerAmount() {
        return beerAmount;
    }

    public void setBeerAmount(Long beerAmount) {
        this.beerAmount = beerAmount;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
}
