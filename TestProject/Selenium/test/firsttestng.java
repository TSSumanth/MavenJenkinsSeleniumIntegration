package test;

import org.testng.annotations.Test;

public class firsttestng {
  @Test
  public void f() {
	  System.out.println("First Test  NGTest");
	  System.out.println(System.getProperty("hostName"));
  }
}
