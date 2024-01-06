package com.org;

import javax.ejb.Remote;
import javax.ejb.Stateless;
 

@Stateless
@Remote(Hello.class)
public class Test implements Hello {
 
    @Override
    public String hello() {
        return  "Hello from EJB!";
    }

}
