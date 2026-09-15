var app = angular.module("departmentApp", []);

app.controller("LoginController", function ($scope, $http) {

    $scope.loginData = {};

    $scope.login = function () {

        $http.post("/login", $scope.loginData)
            .then(function (response) {

                // Store logged-in user details
                localStorage.setItem(
                    "username",
                    response.data.username
                );

                localStorage.setItem(
                    "role",
                    response.data.role
                );

                localStorage.setItem(
                    "staffName",
                    response.data.staffName
                );

                // Go to dashboard
                window.location.href = "index.html";

            })
            .catch(function (error) {

                console.log("Login error:", error);

                $scope.errorMessage =
                    "Invalid username or password";

            });
    };

});