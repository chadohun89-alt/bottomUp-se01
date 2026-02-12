

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import DepartmentDTO;
//import DoctorDTO;

public class DoctorDAO {

	String dbDrv = "com.mysql.cj.jdbc.Driver";
	
	String dbUrl = "jdbc:mysql://codevlab.kr:3306/bottomup2?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
	String dbUsr = "bottomup2";
	String dbPwd = "bottomup22";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// close 메서드
	private void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // close 메서드 밖
	
	// 1번 select(목록) 메서드
	public List<DoctorDTO> getSelcetAll() {
		List<DoctorDTO> list = new ArrayList<>();

		try {
			Class.forName(dbDrv);
			conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
			// --------------------------------------------
			String sql = "select do.*, de.department_name "
					+ "from doctor do join department de "
					+ "on do.department_id = de.department_id "
					+ "order by doctor_id asc";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DoctorDTO dto = new DoctorDTO();
				dto.setDoctorId(rs.getInt("doctor_id"));
				dto.setDoctorName(rs.getString("doctor_name"));
				dto.setDepartmentId(rs.getInt("department_id"));
				dto.setDepartmentName(rs.getString("department_name"));
				dto.setPhone(rs.getString("phone"));
				dto.setCreatedAt(rs.getDate("created_at"));
				list.add(dto);
			}

			// --------------------------------------------
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 또는 접속 실패..");
		} finally {
			dbClose(rs, pstmt, conn);
		}

		return list;

	} // select 메서드 밖	
	
	// 2번 insert 메서드
	public int setInsert(DoctorDTO dto) {

		int result = 0;

		try {
			Class.forName(dbDrv);
			conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
			// --------------------------------------------
			String sql = "";
			sql += "insert into doctor (doctor_id, doctor_name, department_id, phone, created_at) values";
			sql += "(null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getDoctorName());
			pstmt.setLong(2, dto.getDepartmentId());
			pstmt.setString(3, dto.getPhone());

			result = pstmt.executeUpdate(); // 0, 1

			// --------------------------------------------
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 또는 접속 실패..");
		} finally {
			dbClose(rs, pstmt, conn);
		}

		return result;
	} // insert 메서드 밖
	
	// 3번 수정 메서드 update student set phone =?, address = ? where hakbun = ?
	
	public DoctorDTO setUpdate(DoctorDTO dto) {
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName(dbDrv);
			conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
			// --------------------------------------------
			
			String sql = "update doctor set department_id =?, phone = ? where doctor_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, dto.getDepartmentId());
			pstmt.setString(2, dto.getPhone());
			pstmt.setLong(3, dto.getDoctorId());
			int result = pstmt.executeUpdate(); // 0, 1
			
			if(result == 1) System.out.println("수정성공~!");
			

			// --------------------------------------------
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 또는 접속 실패..");
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return dto;
	}

	// 4번 삭제 메서드
	public int setDelete (DoctorDTO dto) {	
	
		int result = 0;
		
		try {
			Class.forName(dbDrv);
			conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
			// --------------------------------------------
			String sql = "delete from doctor where doctor_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, dto.getDoctorId());

			result = pstmt.executeUpdate(); // 0, 1

			// --------------------------------------------
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 또는 접속 실패..");
		} finally {
			dbClose(rs, pstmt, conn);
		}
		
		
		return result;
	}

}
