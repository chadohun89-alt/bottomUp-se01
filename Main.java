
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		RecordManager manager = new RecordManager();

		while (true) {
			System.out.println("\n 진료 기록 관리 시스템");
			System.out.println("1. 진료 기록 등록 | 2. 환자별 이력 조회 | 3. 종료");
			System.out.print("선택> ");

			int menu = Integer.parseInt(sc.nextLine());

			if (menu == 1) { // 1. 등록 (이름 + 주민번호로 중복 방지하여 등록)
				System.out.print("환자 성함: ");
				String patientName = sc.nextLine();
				System.out.print("환자 주민번호: ");
				String ssn = sc.nextLine();
				System.out.print("의사 ID: ");
				String docId = sc.nextLine();
				System.out.print("진단명: ");
				String diag = sc.nextLine();
				System.out.print("처방내용: ");
				String treat = sc.nextLine();

				manager.addRecord(new PatientRecord(patientName, ssn, diag, treat, docId, ""));

			} else if (menu == 2) { // 2. 조회 (이름 + 주민번호로 정확하게 찾기)
				System.out.print("조회할 환자 성함: ");
				String patientName = sc.nextLine();
				System.out.print("조회할 환자 주민번호: ");
				String ssn = sc.nextLine();

				PatientRecord res = manager.findRecord(patientName, ssn);
				if (res != null)
					res.showInfo();
				else
					System.out.println(" 기록이 없습니다.");
			} else {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}