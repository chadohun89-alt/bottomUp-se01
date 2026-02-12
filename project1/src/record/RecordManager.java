package record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordManager {
	private static final String URL = "jdbc:mysql://codevlab.kr:3306/bottomup2?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
	private static final String USER = "bottomup2";
	private static final String PASS = "bottomup22";

	//  진료 기록 등록 (medical_record 테이블)
	public void addRecord(PatientRecord record) {
		String sql = "INSERT INTO medical_record (patient_id, doctor_id, diagnosis, treatment, reg_date) "
				+ "VALUES ((SELECT patient_id FROM patient WHERE SSN = ?), ?, ?, ?, NOW())";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, record.getSsn());
			pstmt.setString(2, record.getDoctor()); // 의사 ID 입력
			pstmt.setString(3, record.getDiagnosis());
			pstmt.setString(4, record.getTreatment());
			pstmt.executeUpdate();
			System.out.println(" 진료 기록이 성공적으로 저장되었습니다.");
		} catch (SQLException e) {
			System.out.println(" 등록 실패: " + e.getMessage());
		}
	}

	//  환자별 진료 이력 조회
	public PatientRecord findRecord(String name, String ssn) {
		String sql = "SELECT p.patient_name, p.SSN, m.diagnosis, m.treatment, m.doctor_id, m.reg_date "
				+ "FROM medical_record m JOIN patient p ON m.patient_id = p.patient_id "
				+ "WHERE p.patient_name = ? AND p.SSN = ? ORDER BY m.reg_date DESC LIMIT 1";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, ssn);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new PatientRecord(rs.getString("patient_name"), rs.getString("SSN"),
							rs.getString("diagnosis"), rs.getString("treatment"), rs.getString("doctor_id"),
							rs.getString("reg_date"));
				}
			}
		} catch (SQLException e) {
			System.out.println(" 조회 실패: " + e.getMessage());
		}
		return null;
	}
}