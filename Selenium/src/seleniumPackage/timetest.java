package seleniumPackage;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class timetest {

	public static void main(String args[]) throws InterruptedException {
		Calendar cal = Calendar.getInstance();
		
		int res = cal.getActualMaximum(Calendar.DATE);
		//TimeUnit.MINUTES.sleep(5);
		System.out.println(cal.get(Calendar.HOUR));
		System.out.println(res);
	}
}
