var app = angular.module("departmentApp", []);

app.controller("MainController", function ($scope, $http) {
    // Get logged-in user details
    $scope.newStudent = {};
    $scope.loggedInUser = localStorage.getItem("username");
    $scope.loggedInRole = localStorage.getItem("role");
    $http.defaults.headers.common["Role"] = $scope.loggedInRole;

    // Protect dashboard
    if (!$scope.loggedInUser) {
        window.location.href = "login.html";
        return;
    }

    // Get students
    $http.get("/students")
        .then(function (response) {
            $scope.studentCount = response.data.length;
            $scope.students = response.data;
        })
        .catch(function (error) {
            console.log("Error loading students:", error);
            $scope.studentCount = 0;
        });


    // Get staff
    $http.get("/staff")
        .then(function (response) {
            $scope.staffCount = response.data.length;
            $scope.staff = response.data;
        })
        .catch(function (error) {
            console.log("Error loading staff:", error);
            $scope.staffCount = 0;
        });
    // Get announcements
    $http.get("/announcements")
        .then(function (response) {
            $scope.announcements = response.data;
        })
        .catch(function (error) {
            console.log("Error loading announcements:", error);
            $scope.announcements = [];
        });
    // Get events
    $http.get("/events")
        .then(function (response) {
            $scope.events = response.data;
        })
        .catch(function (error) {
            console.log("Error loading events:", error);
            $scope.events = [];
        });
    // Get placements
    $http.get("/placements")
        .then(function (response) {
            $scope.placements = response.data;
        })
        .catch(function (error) {
            console.log("Error loading placements:", error);
            $scope.placements = [];
        });
    $scope.addStudent = function () {

        console.log("Student data being sent:", $scope.newStudent);

        $http.post("/students", $scope.newStudent)
            .then(function (response) {

                $scope.students.push(response.data);
                $scope.studentCount = $scope.students.length;
                $scope.newStudent = {};

                alert("Student added successfully!");

            })
            .catch(function (error) {

                console.log("Error adding student:", error);

                if (error.status === 403) {
                    alert("Only ADMIN can add students.");
                } else {
                    alert("Failed to add student.");
                }

            });
    };
    $scope.newStaff = {};


    $scope.addStaff = function () {

        $http.post("/staff", $scope.newStaff)
            .then(function (response) {

                $scope.staff.push(response.data);

                $scope.staffCount = $scope.staff.length;

                $scope.newStaff = {};

                alert("Staff added successfully!");

            })
            .catch(function (error) {

                console.log("Error adding staff:", error);

                alert("Failed to add staff.");

            });
    };
    $scope.newAnnouncement = {};

    $scope.addAnnouncement = function () {

        $http.post("/announcements", $scope.newAnnouncement)
            .then(function (response) {

                $scope.announcements.push(response.data);

                $scope.newAnnouncement = {};

                alert("Announcement added successfully!");

            })
            .catch(function (error) {

                console.log("Error adding announcement:", error);

                alert("Failed to add announcement.");

            });
    };
    $scope.newEvent = {};

    $scope.addEvent = function () {

        $http.post("/events", $scope.newEvent)
            .then(function (response) {

                $scope.events.push(response.data);

                $scope.newEvent = {};

                alert("Event added successfully!");

            })
            .catch(function (error) {

                console.log("Error adding event:", error);

                alert("Failed to add event.");

            });
    };
    $scope.newPlacement = {};

    $scope.addPlacement = function () {

        $http.post("/placements", $scope.newPlacement)
            .then(function (response) {

                $scope.placements.push(response.data);

                $scope.newPlacement = {};

                alert("Placement added successfully!");

            })
            .catch(function (error) {

                console.log("Error adding placement:", error);

                alert("Failed to add placement.");

            });
    };
    // Logout
    $scope.logout = function () {

        localStorage.removeItem("staffName");
        localStorage.removeItem("username");
        localStorage.removeItem("role");

        window.location.href = "login.html";
    };
    // Edit Student
    $scope.editStudent = function (student) {

        var updatedStudent = {
            registerNumber: prompt("Enter Register Number:", student.registerNumber),
            name: prompt("Enter Name:", student.name),
            email: prompt("Enter Email:", student.email),
            phone: prompt("Enter Phone:", student.phone),
            year: prompt("Enter Year:", student.year),
            section: prompt("Enter Section:", student.section)
        };

        if (!updatedStudent.name) {
            return;
        }

        $http.put("/students/" + student.studentId, updatedStudent)
            .then(function (response) {

                var index = $scope.students.indexOf(student);

                $scope.students[index] = response.data;

                alert("Student updated successfully!");

            })
            .catch(function (error) {

                console.log("Error updating student:", error);

                alert("Failed to update student.");

            });
    };
    // Delete Student
    $scope.deleteStudent = function (student) {

        if (!confirm("Are you sure you want to delete this student?")) {
            return;
        }

        $http.delete("/students/" + student.studentId, {
            transformResponse: [function (data) {
                return data;
            }]
        })
            .then(function () {

                var index = $scope.students.indexOf(student);

                if (index !== -1) {
                    $scope.students.splice(index, 1);
                }

                $scope.studentCount = $scope.students.length;

                alert("Student deleted successfully!");

            })
            .catch(function (error) {

                console.log("Error deleting student:", error);

                alert("Failed to delete student.");

            });
    };
    // Edit Staff
    $scope.editStaff = function (index) {

        var staff = $scope.staff[index];

        console.log("Selected Staff:", staff);
        console.log("Staff ID:", staff.staffId);

        var updatedStaff = {
            staffName: prompt("Enter Staff Name:", staff.staffName),
            email: prompt("Enter Email:", staff.email),
            phone: prompt("Enter Phone:", staff.phone),
            designation: prompt("Enter Designation:", staff.designation),
            username: staff.username,
            password: staff.password,
            role: staff.role
        };

        if (!updatedStaff.staffName) {
            return;
        }

        $http.put("/staff/" + staff.staffId, updatedStaff)
            .then(function (response) {

                $scope.staff[index] = response.data;

                alert("Staff updated successfully!");

            })
            .catch(function (error) {

                console.log("Error updating staff:", error);

                alert("Failed to update staff.");

            });
    };
    // Delete Staff
    $scope.deleteStaff = function (index) {

        var staff = $scope.staff[index];

        if (!confirm("Are you sure you want to delete this staff member?")) {
            return;
        }

        console.log("Deleting Staff ID:", staff.staffId);

        $http.delete("/staff/" + staff.staffId)
            .then(function () {

                $scope.staff.splice(index, 1);

                $scope.staffCount = $scope.staff.length;

                alert("Staff deleted successfully!");

            })
            .catch(function (error) {

                console.log("Error deleting staff:", error);

                alert("Failed to delete staff.");

            });
    };
});