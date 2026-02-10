function searchPatient() {
    var name = document.getElementById("userName").value;
    var phone = document.getElementById("userPhone").value;
    var ssn = document.getElementById("userSsn").value;
    

    var diagnosis = "기록 없음";
    var treatment = "기록 없음";
    var regDate = "-";
    var doctor = "담당 없음"

    if ((name == "홍길동" && phone == "010-1234-5678") || ssn == "123456-1234567") {
        diagnosis = "급성 인후염";
        treatment = "약 3일분 처방";
        regDate = "2026-05-20";
        doctor = "임꺽정";
    }

    // 1. 빈 창 열기
    var win = window.open("", "ResultWindow", "width=400, height=400");

    
    // 새 창의 body 안에 HTML 코드를 통째로 넣기.
    win.document.body.innerHTML = 
        "<h2>🔍 진료 기록 결과</h2>" +
        "<p><b>환자성함:</b> " + name + "</p>" +"<p><b>담당의사:</b> " + doctor + "</p>"+ 
        "<hr>" +
        "<p><b>진단명:</b> " + diagnosis + "</p>" +
        "<p><b>처방내용:</b> " + treatment + "</p>" +
        "<p><b>등록일자:</b> " + regDate + "</p>" +
        "<br><button onclick='window.close()'>창 닫기</button>";
    
   
}