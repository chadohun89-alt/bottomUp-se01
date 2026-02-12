import java.sql.*;
import java.util.Scanner;

public class patient {
    public static void main(String[] args) {
        // 접속 정보 (알려주신 정보 반영)
        String url = "jdbc:mysql://codevlab.kr:3306/bottomup2";
        String user = "bottomup2";
        String password = "bottomup22";
        Scanner sc = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   🏥 [bottomup] 환자 관리 시스템 접속");
        System.out.println("==========================================");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            while (true) {
                System.out.println("\n[메뉴 선택]");
                System.out.println("1. 환자 목록 조회");
                System.out.println("2. 새 환자 등록");
                System.out.println("3. 시스템 종료");
                System.out.print("입력 > ");
                
                int choice = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기

                if (choice == 1) {
                    // [조회 기능]
                    String sql = "SELECT * FROM patient ORDER BY patient_id ASC";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    System.out.println("\n--------------------------------------------------");
                    System.out.println("번호\t이름\t주민등록번호\t\t연락처");
                    System.out.println("--------------------------------------------------");
                    while (rs.next()) {
                        System.out.printf("%d\t%s\t%s\t%s\n", 
                            rs.getInt("patient_id"), 
                            rs.getString("patient_name"), 
                            rs.getString("SSN"), 
                            rs.getString("phone"));
                    }
                    System.out.println("--------------------------------------------------");

                } else if (choice == 2) {
                    // [등록 기능]
                    System.out.println("\n[신규 환자 정보 입력]");
                    System.out.print("성함: "); String name = sc.nextLine();
                    System.out.print("주민번호(예: 000101-3123456): "); String ssn = sc.nextLine();
                    System.out.print("연락처(예: 010-1234-5678): "); String phone = sc.nextLine();

                    String sql = "INSERT INTO patient (patient_name, SSN, phone) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, name);
                    pstmt.setString(2, ssn);
                    pstmt.setString(3, phone);
                    
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        System.out.println("✅ 성공적으로 등록되었습니다!");
                    }

                } else if (choice == 3) {
                    System.out.println("👋 시스템을 종료합니다.");
                    break;
                } else {
                    System.out.println("⚠️ 잘못된 번호입니다. 다시 선택해주세요.");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ 에러 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}