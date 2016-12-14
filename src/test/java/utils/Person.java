package utils;

import java.math.BigDecimal;

/**
 * Created by kangzz on 16/12/14.
 */
public class Person {
    private String name;
    private Integer age;
    private Double money;
    private BigDecimal abc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public BigDecimal getAbc() {
        return abc;
    }

    public void setAbc(BigDecimal abc) {
        this.abc = abc;
    }
}
