package com.example.domain;

// lombok을 통해 @Data, @AllArgsConstructor, @NoArgsConstructor를 선언해주면 생성자를 만들어 주는데 lombok을 안 쓰면 일일이 다 써줘야되는 듯
public class SampleVO {
	private Integer mno;
	private String firstName;
	private String lastName;
	
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public SampleVO(Integer mno, String firstName, String lastName) {
		this.mno = mno;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "SampleVO [mno=" + mno + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}
