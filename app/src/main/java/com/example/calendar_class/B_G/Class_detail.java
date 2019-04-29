package com.example.calendar_class.B_G;

public class Class_detail {
	private String Name;
	private String Place_Teacher;
	private String Time;
	private int Day;
	private int Week;
	private int Style;
	public Class_detail(String Name,String Place_Teacher,String Time,int Day,int Week,int Style) {
		this.Name=Name;
		this.Place_Teacher=Place_Teacher;
		this.Time=Time;
		this.Day=Day;
		this.Week=Week;
		this.Style=Style;
	}
	public String getName() {
		return Name;
	}
	public String getPlace_Teacher() {
		return Place_Teacher;
	}
	public String getTime() {
		return Time;
	}
	public int getDay() {
		return Day;
	}
	public int getWeek() {
		return Week;
	}
	public int getStyle() {
		return Style;
	}

}
