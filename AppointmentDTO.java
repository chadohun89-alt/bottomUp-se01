package app;

public class AppointmentDTO {
    private int appId;
    private int patientId;
    private int doctorId;
    private String appDate;
    private String situation;

    public AppointmentDTO() {}

    public AppointmentDTO(int appId, int patientId, int doctorId, String appDate, String situation) {
        this.appId = appId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appDate = appDate;
        this.situation = situation;
    }

    public int getAppId() { return appId; }
    public void setAppId(int appId) { this.appId = appId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public String getAppDate() { return appDate; }
    public void setAppDate(String appDate) { this.appDate = appDate; }

    public String getSituation() { return situation; }
    public void setSituation(String situation) { this.situation = situation; }

    @Override
    public String toString() {
        return String.format("[번호:%d] 환자:%d | 의사:%d | 일시:%s | 상태:%s", 
                             appId, patientId, doctorId, appDate, situation);
    }
}