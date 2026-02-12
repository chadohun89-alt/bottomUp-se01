
public class PatientRecord {
	private String patientName, ssn, diagnosis, treatment, doctorName, regDate;

	public PatientRecord(String patientName, String ssn, String diagnosis, String treatment, String doctorName,
			String regDate) {
		this.patientName = patientName;
		this.ssn = ssn;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.doctorName = doctorName;
		this.regDate = regDate;
	}

	public void showInfo() {
		System.out.println("\n------------[ 진료 이력 ]-------------");
		System.out.println("환자성함: " + patientName + " (" + ssn + ")");
		System.out.println("담당의사: " + doctorName);
		System.out.println("진단내용: " + diagnosis);
		System.out.println("처방내용: " + treatment);
		System.out.println("진료일자: " + regDate);
		System.out.println("------------------------------------");
	}

	// Getter 생략 (기존과 동일)
	public String getName() {
		return patientName;
	}

	public String getSsn() {
		return ssn;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public String getDoctor() {
		return doctorName;
	}
}