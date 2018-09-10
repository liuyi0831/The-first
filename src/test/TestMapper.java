package test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMapper {
	public static void main(String[] args) throws ParseException {
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date= sdf.parse("2018-2-2");
		System.out.println("date:"+date);
	
		Timestamp timeStamp = new Timestamp(date.getTime());
		System.out.println(timeStamp);*/
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		System.out.println(sdf.format(date));//将date转化为string
		String date1="2018-08-25 09:53:15";
		Date date2=sdf.parse(date1);
		System.out.println(date1);//将string转化为date
		Long long1=date.getTime()-date2.getTime();
		System.out.println(long1/(60*60*1000));//相差时间
		
	}
}
