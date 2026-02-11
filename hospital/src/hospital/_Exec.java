package hospital;

import java.util.List;
import java.util.Scanner;

import hospital.DoctorDAO;
import hospital.DoctorDTO;

public class _Exec {

	public static void main(String[] args) {
		// 의사 등록 기능 insert into
		// 의사 명단 출력 기능 select * from
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("선택(1:목록, 2:등록, 3:수정, 4:삭제, 기타:종료):");
			String menu = sc.nextLine();
			DoctorDAO dao = new DoctorDAO();
			DoctorDTO dto = new DoctorDTO();
			int result = 0;

			switch (menu) {
			case "1": // 목록

				List<DoctorDTO> list = dao.getSelcetAll();
				System.out.println("번호 \t 이름 \t 부서번호 \t 부서명 \t 연락처 \t 등록일");
				System.out.println("----------------------------------------------");
				for (int i = 0; i < list.size(); i++) {
					list.get(i).display();
				}
				break;


			case "2": // 등록
				dto.inputField("chuga");
//				StudentDAO dao = new StudentDAO();
				result = dao.setInsert(dto);

				System.out.println("result : " + result);
				break;

			case "3": // 수정
				System.out.println("- 수정 -");
				dto.inputField("sujung");
				dao.setUpdate(dto);			

				break;

			case "4": // 삭제
				System.out.println("- 삭제 -");
				dto.inputField("sakje");
				result = dao.setDelete(dto);
				System.out.println("result : " + result);

				break;

			default:
				System.out.println("- 프로그램 종료 -");
				return;
			}

		}

	}

}
