function searchPatient() {
    const name = document.getElementById("userName").value;
    const phone = document.getElementById("userPhone").value;
    const ssn = document.getElementById("userSsn").value;

    let diagnosis = "기록 없음", treatment = "기록 없음", regDate = "-", doctor = "담당 없음";

    if (name === "홍길동" && phone === "010-1234-5678" && ssn === "123456-1234567") {
        diagnosis = "급성 인후염";
        treatment = "약 3일분 처방";
        regDate = "2026-05-20";
        doctor = "임꺽정";
    }
    else if(name === "김철수" && phone === "010-1111-2222" && ssn === "123456-4567890") {
        diagnosis= "알레르기성 비염";
        treatment= "안약 및 스프레이";
        regDate= "2026-06-12";
        doctor= "허준";
    }


    // 모달 내용 생성
    const modalBody = document.getElementById("modalBody");
    modalBody.innerHTML = `
        <p><b>환자성함:</b> ${name}</p>
        <p><b>담당의사:</b> ${doctor}</p>
        <hr>
        <p><b>진단명:</b> ${diagnosis}</p>
        <p><b>처방내용:</b> ${treatment}</p>
        <p><b>등록일자:</b> ${regDate}</p>
    `;

    // 모달 열기
    const modal = document.getElementById("resultModal");
    modal.showModal(); 
}

function closeModal() {
    const modal = document.getElementById("resultModal");
    modal.close();
}