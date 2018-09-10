package com.cx.pojo;

public class UserOrder extends Order{
private String flightftime;
private String flightname;
private String flightid;
public String getFlightid() {
	return flightid;
}
public void setFlightid(String flightid) {
	this.flightid = flightid;
}
public String getFlightftime() {
	return flightftime;
}
public void setFlightftime(String flightftime) {
	this.flightftime = flightftime;
}
public String getFlightname() {
	return flightname;
}
public void setFlightname(String flightname) {
	this.flightname = flightname;
}
@Override
public String toString() {
	return "UserOrder [flightftime=" + flightftime + ", flightname=" + flightname + ", flightid=" + flightid + "]";
}

}
