// [기능 3] 예약 취소 

    public void cancelWithAuth(int appId, String name, String phone) {
        String sql = "UPDATE appointments a"+
                     "JOIN patients p ON a.patient_id = p.patient_id"+
                     "SET a.situation = '취소완료'"+
                     "WHERE a.app_id = ? AND p.patient_name = ? AND p.phone = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appId);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            
            if (pstmt.executeUpdate() > 0) {
                System.out.println("예약 번호 ["+appId+"]번이 [취소완료] 되었습니다.");
            } else {
                System.out.println("취소 실패: 정보가 일치하지 않거나 이미 취소된 상태입니다.");
            }
        } catch (SQLException e) {
            System.err.println("취소 오류: " +e.getMessage());
        }