package java260123_2;

import java.util.Scanner;

public class AppointmentMain {
    public static void main(String[] args) {
        AppointmentDAO dao = new AppointmentDAO();
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        System.out.println("========================================");
        System.out.println("   [병원 예약 관리 시스템 - 통합 관리자용]");
        System.out.println("========================================");

        while (run) {
            System.out.println("\n1. 예약등록 | 2. 전체조회 | 3. 이름검색 | 4. 예약변경 | 5. 예약취소 | 6. 종료");
            System.out.print("메뉴 선택 >> ");
            
            int menu;
            try {
                menu = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
                continue;
            }

            switch (menu) {
                case 1: // 예약 등록 
                    System.out.println("\n[새 예약 등록]");
                    System.out.print("환자 ID 번호: ");
                    int pId = Integer.parseInt(sc.nextLine());
                    System.out.print("담당 의사 ID 번호: ");
                    int dId = Integer.parseInt(sc.nextLine());
                    System.out.print("예약 날짜(예: 2026-02-11 14:30:00): ");
                    String date = sc.nextLine();
                    System.out.print("예약 상태 및 내역: ");
                    String sit = sc.nextLine();
                    dao.insertAppointment(pId, dId, date, sit);
                    break;

                case 2: // 전체 목록
                    System.out.println("\n[전체 예약 목록 조회]");
                    dao.printAllAppointments();
                    break;

                case 3: // 이름으로 검색
                    System.out.print("\n검색할 환자 성함: ");
                    String searchName = sc.nextLine();
                    dao.searchByPatientName(searchName);
                    break;

                case 4: // 예약 정보 변경
                    System.out.print("\n변경할 예약 번호(app_id): ");
                    int changeId = Integer.parseInt(sc.nextLine());
                    System.out.print("새로운 예약 일시: ");
                    String newDate = sc.nextLine();
                    System.out.print("변경할 상태(예: 예약변경, 진료완료 등): ");
                    String newSit = sc.nextLine();
                    
                    dao.changeAppointment(changeId, newDate, newSit);
                    break;

                case 5: // 예약 취소
                    System.out.print("\n취소할 예약 번호(app_id): ");
                    int deleteId = Integer.parseInt(sc.nextLine());
                    System.out.print("해당 예약을 정말 취소하시겠습니까? (Y/N): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        dao.deleteAppointment(deleteId);
                    } else {
                        System.out.println("취소 요청이 철회되었습니다.");
                    }
                    break;

                case 6: // 종료
                    System.out.println("시스템을 종료합니다.");
                    run = false;
                    break;

                default:
                    System.out.println("잘못된 메뉴 선택입니다. 다시 입력해 주세요.");
            }
        }
        sc.close();
    }
}