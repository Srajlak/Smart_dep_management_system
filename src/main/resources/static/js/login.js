var app = angular.module("departmentApp", []);

app.controller("LoginController", function ($scope, $http) {

    $scope.loginData = {};
    $scope.errorMessage = "";

    $scope.login = function () {

        // Clear previous error
        $scope.errorMessage = "";

        console.log("Sending login data:", $scope.loginData);

        $http.post("/login", $scope.loginData)

            .then(function (response) {

                console.log("Login successful:", response.data);

                // Store username
                localStorage.setItem(
                    "username",
                    response.data.username
                );

                // Store role
                localStorage.setItem(
                    "role",
                    response.data.role
                );

                // Store staff name if available
                if (response.data.staffName) {
                    localStorage.setItem(
                        "staffName",
                        response.data.staffName
                    );
                }

                // Store student name if available
                if (response.data.studentName) {
                    localStorage.setItem(
                        "studentName",
                        response.data.studentName
                    );
                }

                // Go to dashboard
                window.location.href = "dashboard.html";
            })

            .catch(function (error) {

                console.log("Login failed:", error);
                console.log("Backend response:", error.data);

                if (error.data) {
                    $scope.errorMessage = error.data;
                } else {
                    $scope.errorMessage =
                        "Invalid username or password";
                }
            });
    };

});