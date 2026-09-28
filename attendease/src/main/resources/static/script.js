function addStudent() {

    const student = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    fetch("/api/students", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(student)
    })
        .then(response => response.json())
        .then(data => {
            alert("Student Added");
            loadStudents();
        });
}

function loadStudents() {

    fetch("/api/students")
        .then(response => response.json())
        .then(data => {

            const list =
                document.getElementById("studentList");

            list.innerHTML = "";

            data.forEach(student => {

                list.innerHTML +=
                    `<li>${student.name} - ${student.email}</li>`;
            });
        });
}

function getPercentage() {

    const id =
        document.getElementById("studentId").value;

    fetch("/api/attendance/percentage/" + id)
        .then(response => response.text())
        .then(data => {

            document.getElementById(
                "percentageResult"
            ).innerText =
                "Attendance Percentage : "
                + parseFloat(data).toFixed(2)
                + "%";
        });
}

function getBelowThreshold() {

    const threshold =
        document.getElementById("threshold").value;

    fetch("/api/attendance/below/" + threshold)
        .then(response => response.json())
        .then(data => {

            const list =
                document.getElementById("belowList");

            list.innerHTML = "";

            data.forEach(student => {

                list.innerHTML +=
                    `<li>${student.name} - ${student.email}</li>`;
            });
        });
}