// 1. 데이터 관리 
const patientNames = { 
    "1": "김철수", 
    "2": "이영희", 
    "3": "박민수", 
    "4": "최다인" };

const doctorNames = { 
    "1": "김이선 선생님 (내과)", 
    "2": "이박선 선생님 (외과)", 
    "3": "박치달 선생님 (소아과)", 
    "4": "정후임 선생님 (피부과)" 
};

let virtualDB = [
    { app_id: 1, patient_id: 1, doctor_id: 2, app_date: "2024-05-20 10:30", situation: "예약" }
];

// 2. 화면 목록
function renderTable() {
    const tbody = document.getElementById('appointmentTable');
    if (!tbody) return;

    tbody.innerHTML = "";

    // for 반복문
    for (let i = 0; i < virtualDB.length; i++) {
        let row = virtualDB[i];
        
        // 환자, 의사 이름 찾기
        let pName = patientNames[row.patient_id];
        let dName = doctorNames[row.doctor_id];

        //문자열
        let htmlTag = "<tr>";
        htmlTag += "<td style='text-align:center'>" + row.app_id + "</td>";
        htmlTag += "<td>" + pName + " (#" + row.patient_id + ")</td>";
        htmlTag += "<td>" + dName + "</td>";
        htmlTag += "<td>" + row.app_date + "</td>";
        htmlTag += "<td style='text-align:center'><span class='status'>" + row.situation + "</span></td>";
        htmlTag += "</tr>";

        // 테이블 추가
        tbody.innerHTML = tbody.innerHTML + htmlTag;
    }
}

// 3. 예약 추가 
function addAppointment() {
    // 입력값 가져오기
    const pId = document.getElementById('patientSelect').value;
    const dId = document.getElementById('doctorSelect').value;
    const dateInput = document.getElementById('appDate').value;
    const sit = document.getElementById('situation').value;

    if (dateInput == "") {
        alert("예약 일시를 선택해주세요.");
        return;
    }

    // 새로운 ID 생성
    let newId = virtualDB.length + 1;
    
    // 새로운 객체 생성
    let newRecord = {
        app_id: newId, 
        patient_id: pId, 
        doctor_id: dId, 
        app_date: dateInput, 
        situation: sit
    };

    // 배열에 추가
    virtualDB.push(newRecord);

    renderTable();
    
    // 알림 메시지
    alert("예약이 저장되었습니다.");
}

window.onload = function() {
    renderTable();
};