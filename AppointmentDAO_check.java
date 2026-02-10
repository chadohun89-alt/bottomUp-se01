// [기능 2] 예약 내역 확인 
    
    public void printAppointments(String name, String phone) {
        String sql = "SELECT a.app_id, a.app_date, d.doctor_name, a.situation " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "WHERE p.patient_name = ? AND p.phone = ? ORDER BY a.app_date ASC";

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("      ["+name+"]님의 예약 내역");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    System.out.printf("[번호:%d] 일시:%s | 의사:%s | 상태:%s%n", 
                        rs.getInt("app_id"), rs.getString("app_date"), 
                        rs.getString("doctor_name"), rs.getString("situation"));
                }
                if (!hasData) System.out.println("검색된 예약 내역이 없습니다.");
            }
        } catch (SQLException e) {
            System.err.println("조회 오류: " +e.getMessage());
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
