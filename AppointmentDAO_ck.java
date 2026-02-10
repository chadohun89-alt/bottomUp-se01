package java260123_2;

public class AppointmentDAO {
	
}
 // [기능 2] 예약 내역 확인 
    
    public void printAppointments(String name, String phone) {
        String sql = "SELECT a.app_id, a.app_date, d.doctor_name, a.situation " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "WHERE p.patient_name = ? AND p.phone = ? ORDER BY a.app_date ASC";
    }

}
