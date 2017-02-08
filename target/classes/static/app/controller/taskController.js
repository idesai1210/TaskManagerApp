TaskManagerApp.controller('tasksCtrl', function ($scope, $http, $location, $localStorage, ngDialog) {
    $scope.model = {brand: {}};
    $scope.listBrand = [];
    $scope.listModel = [{
            brand: {}
        }];

    $scope.show = "true";
    $scope.hide = "true";


   //$scope.selection = [];

    $scope.toggle=true;
    	$scope.selection = [];
    	$scope.statuses=['ACTIVE','COMPLETED'];
    	$scope.priorities=['HIGH','LOW','MEDIUM'];
    	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";

    //Show lists of models
    //$scope.flag = "Ishan";
    $http({
        method: 'GET',
        url: '/tasks',
        data: $scope.selection,
        headers: {'Content-Type': 'application/json'}

    }).then(function (data) {
       $scope.tasks = data;
       console.log($scope.tasks);
       	        for(var i=0;i<$scope.tasks.data.length;i++){
       	            if($scope.tasks.data[i].taskStatus=='ACTIVE'){
       	           	 $scope.selection.push($scope.tasks.data[i].taskId);
       	        }
       	        }

    });

    //add a new task
    	$scope.addTask = function addTask() {
    		if($scope.taskName=="" || $scope.taskDesc=="" || $scope.taskPriority == "" || $scope.taskStatus == ""){
    			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
    		}
    		else{
    		  $http({
                      method: 'POST',
                      url: '/tasks/insert/' +$scope.taskName+'/'+$scope.taskDesc+'/'+$scope.taskPriority+'/'+$scope.taskStatus,
                      data: $scope.selection,
                      headers: {'Content-Type': 'application/json'}

                  }).then(function(data) {
    			 alert("Task added");
    			 $scope.tasks = data;
    			 $scope.taskName="";
    			 $scope.taskDesc="";
    			 $scope.taskPriority="";
    			 $scope.taskStatus="";
    			 $scope.toggle='!toggle';
    			 $location.path('/tasks/insert');
    		    });
    		}
    	};
/*
    //Post model.
    $scope.createModel = function () {
        if ($scope.model.nameModel == '' || $scope.model.nameModel == null || $scope.model.brand.idBrand == '' || $scope.model.brand.idBrand == null) {
            ngDialog.open({
                scope: $scope,
                template: 'webapp/resources/static/img/modal.html',
                className: 'ngdialog-theme-default',
                width: 650,
                lain: true
            });
        } else {
            $http({
                method: 'POST',
                url: 'model',
                data: $scope.model,
                headers: {'Content-Type': 'application/json'}
            }).success(function (data) {
                $location.path('/listmodels');
            });
        }
    };

    //Open options to edit.
    $scope.editModel = function (idModel) {
        $scope.show = "false";
        $scope.hide = "false";
        $scope.hideObj = "true";
        $scope.showObj = "true";

        $scope.idModel = idModel;
    };

    //Back to normal.
    $scope.undoEdit = function () {
        $scope.show = "true";
        $scope.hide = "true";
        $scope.hideObj = "false";
        $scope.showObj = "false";

        $scope.idModel = 0;
    };

    //Save changes made in the selected field.
    $scope.saveModel = function (model) {
        $http({
            method: 'PUT',
            url: 'updateModel',
            data: model,
            headers: {'Content-Type': 'application/json'}
        }).success(function (model) {
            ngDialog.open({
                scope: $scope,
                template: 'webapp/resources/static/img/modalSuccess.html',
                className: 'ngdialog-theme-default',
                width: 650,
                lain: true
            });
            $scope.show = "true";
            $scope.hide = "true";
            $scope.hideObj = "false";
            $scope.showObj = "false";

            $scope.idModel = 0;
        });
    };

    $scope.deleteModel = function (model) {
        ngDialog.openConfirm({
            template: 'webapp/resources/static/img/sure.html',
            className: 'ngdialog-theme-default'
        }).then(function () {
            $http({
                method: 'DELETE',
                url: 'deleteModel',
                data: model,
                headers: {'Content-Type': 'application/json'}
            }).success(function (model) {
                //Update list. Again iIt's not really what I wanted to do.
                $http({
                    method: 'GET',
                    url: 'listmodels',
                    data: $scope.model,
                    headers: {'Content-Type': 'application/json'}
                }).success(function (data) {

                    $scope.listModel = data;
                });

            });
        }, function (reason) {

        });

    };
*/
});