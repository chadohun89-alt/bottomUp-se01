//package java260123_2;

import java.sql.*;

public class AppointmentDAO {
    private static final String URL = "jdbc:mysql://codevlab.kr:3306/bottomup2?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "bottomup2";
    private static final String PASS = "bottomup22";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // 1. 새 예약 저장
    public void insertAppointment(int patientId, int doctorId, String appDate, String situation) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, app_date, situation) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setString(3, appDate);
            pstmt.setString(4, situation);
            pstmt.executeUpdate();
            System.out.println("DB에 예약 정보가 저장되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. 전체 예약 목록 조회
    public void printAllAppointments() {
        String sql = "SELECT * FROM appointments";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("예약번호: " + rs.getInt("app_id") + 
                                   ", 환자ID: " + rs.getInt("patient_id") + 
                                   ", 의사ID: " + rs.getInt("doctor_id") + 
                                   ", 날짜: " + rs.getTimestamp("app_date") + 
                                   ", 상태: " + rs.getString("situation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. 예약 정보 변경 
    public void changeAppointment(int appId, String newDate, String newSituation) {
        String sql = "UPDATE appointments SET app_date = ?, situation = ? WHERE app_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newDate);
            pstmt.setString(2, newSituation);
            pstmt.setInt(3, appId);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("예약 번호 [" + appId + "]의 정보가 변경되었습니다.");
            } else {
                System.out.println("해당 예약 번호를 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. 예약 취소/삭제 (D)
    public void deleteAppointment(int appId) {
        String sql = "DELETE FROM appointments WHERE app_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("예약 번호 [" + appId + "]가 취소되었습니다.");
            } else {
                System.out.println("해당 예약 번호를 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. 환자 이름으로 예약 검색
    public void searchByPatientName(String patientName) {
        String sql = "SELECT a.*, p.name FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "WHERE p.name LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + patientName + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("[" + patientName + "] 검색 결과:");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("예약번호: " + rs.getInt("app_id") + 
                                       " | 환자명: " + rs.getString("name") +
                                       " | 날짜: " + rs.getTimestamp("app_date") + 
                                       " | 상태: " + rs.getString("situation"));
                }
                if (!found) System.out.println("검색 결과가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}