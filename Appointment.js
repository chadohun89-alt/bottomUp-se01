// 1. 데이터 관리 (기존과 동일)
const patientNames = { 
    "101": "김철수", 
    "102": "이영희", 
    "103": "박민수", 
    "104": "최다인" 
};

const doctorNames = { 
    "1": "김이선 선생님 (내과)", 
    "2": "이박선 선생님 (외과)", 
    "3": "박치달 선생님 (소아과)", 
    "4": "정후임 선생님 (피부과)" 
};

let virtualDB = [
    { app_id: 1, patient_id: "101", doctor_id: "2", app_date: "2024-05-20 10:30", situation: "예약" }
];

// 2. 화면 목록
function renderTable() {
    const tbody = document.getElementById('appointmentTable');
    if (!tbody) return;

    const searchText = document.getElementById('searchName') ? document.getElementById('searchName').value.toLowerCase() : "";
    let totalHtml = "";

    for (let i = 0; i < virtualDB.length; i++) {
        let row = virtualDB[i];
        let pName = patientNames[row.patient_id];
        let dName = doctorNames[row.doctor_id];

        // 검색어 필터링
        if (pName.toLowerCase().indexOf(searchText) === -1) {
            continue; 
        }

        // 클래스 부여
        let statusClass = "";
        if (row.situation === "예약") {
            statusClass = "status-blue";
        } else if (row.situation === "취소") {
            statusClass = "status-red";
        }

        // HTML 조립
        totalHtml += "<tr>";
        totalHtml += "<td style='text-align:center'>" + row.app_id + "</td>";
        totalHtml += "<td>" + pName + " (#" + row.patient_id + ")</td>";
        totalHtml += "<td>" + dName + "</td>";
        totalHtml += "<td>" + row.app_date.replace("T", " ") + "</td>"; // T 문자 제거
        totalHtml += "<td style='text-align:center'><span class='" + statusClass + "'>" + row.situation + "</span></td>";
        totalHtml += "</tr>";
    }
    
    // innerHTML 업데이트
    tbody.innerHTML = totalHtml;
}

// 3. 예약 추가 
function addAppointment() {
    const pId = document.getElementById('patientSelect').value;
    const dId = document.getElementById('doctorSelect').value;
    const dateInput = document.getElementById('appDate').value;
    const sit = document.getElementById('situation').value;

    if (dateInput == "") {
        alert("예약 일시를 선택해주세요.");
        return;
    }

    // 새로운 ID 생성
    let newId = virtualDB.length > 0 ? virtualDB[virtualDB.length - 1].app_id + 1 : 1;
    
    let newRecord = {
        app_id: newId, 
        patient_id: pId, 
        doctor_id: dId, 
        app_date: dateInput, 
        situation: sit
    };

    virtualDB.push(newRecord);
    renderTable();
    alert("예약이 저장되었습니다.");
}

window.onload = function() {
    renderTable();
};

function filterAppointments() {
    renderTable();
}