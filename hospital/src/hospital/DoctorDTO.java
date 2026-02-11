package hospital;

import java.sql.Date;
import java.util.Scanner;

public class DoctorDTO {

	private int doctorId;
	private String doctorName;
	private int departmentId;
	private String phone;
	private Date createdAt;
	private String departmentName;
	
	
	// 생성자
	public DoctorDTO() {
		
	}
	
	// method
	public void inputField(String gubun) {
		Scanner sc = new Scanner(System.in);
		
		if(gubun.equals("sujung") || gubun.equals("sakje") ) {
			System.out.println("의사번호 : ");
			String doctorId_ = sc.nextLine();
			this.doctorId = Integer.parseInt(doctorId_);			
		}
		
		if(gubun.equals("chuga")) {
			System.out.print("이름 : ");
			this.doctorName = sc.nextLine();
			System.out.print("부서 번호 : ");
			this.departmentId = sc.nextInt();
			sc.nextLine();
			System.out.print("전화번호 : ");
			this.phone = sc.nextLine();

		} else if(gubun.equals("sujung")) {
			System.out.print("부서번호 : ");
			this.departmentId = sc.nextInt();
			sc.nextLine();
			System.out.print("전화번호 : ");
			this.phone = sc.nextLine();
		}

	}
	
	
	// 조회 화면 메서드
	public void display() {
		System.out.printf("%d \t %s \t %d \t %s \t %s \t %s\n", 
				doctorId, doctorName, departmentId, departmentName, phone, createdAt);
	}


	// getter&setter
	public int getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "DoctorDTO [의사번호=" + doctorId + ", 의사이름=" + doctorName + "]";
	}
	
	
	
	
	
}
