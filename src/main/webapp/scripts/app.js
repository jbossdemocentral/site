var app = angular.module('jbossdemocentral', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);
 
app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/start.html'
    }).when('/datagrid',{
    	templateUrl: 'views/demolist.html',
    	controller: 'JDGCtrl'
    }).when('/eap',{
    	templateUrl: 'views/demolist.html',
    	controller: 'EAPCtrl'
    }).when('/bpms',{
    	templateUrl: 'views/bpms.html'
    }).when('/brms',{
    	templateUrl: 'views/brms.html'
    }).when('/fuse',{
    	templateUrl: 'views/demolist.html',
    	controller: 'FUSECtrl'
    }).when('/dv',{
    	templateUrl: 'views/demolist.html',
    	controller: 'DVCtrl'
    }).when('/integrated',{
    	templateUrl: 'views/demolist.html',
		controller: 'INTEGRATIONCtrl'
    }).otherwise({
        redirectTo: '/'
    });
});

app.controller('TabsCtrl', ['$scope', function ($scope) {
    $scope.tabs = [{title: 'Start',url: '#'},{title: 'Multi product',url: '#integrated'}, {title: 'JBoss EAP',url: '#eap'}, {title: 'DataGrid',url: '#datagrid'}, {title: 'BPM Suite',url: '#bpms'}, {title: 'BRMS',url: '#brms'},{title: 'Fuse',url: '#fuse'},{title: 'Data Virtualization',url: '#dv'}];

    $scope.currentTab = '#';

    $scope.onClickTab = function (tab) {
        $scope.currentTab = tab.url;
    }
    
    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }
}]);

app.controller('JDGCtrl', function ($scope, $http) {
	$scope.pageHeading='JBoss Data Grid Demos'
	$scope.demos = new Array();
	$http.get('rest/demos/jdg').success(function (demos) {
    	demos.forEach(function(demo) { $scope.demos.push(demo)});	
    }).error(function (data, status,headers) {
		if(status=403 && data.message.match("API rate limit exceeded")) {		
			$scope.alerts=[{type: 'danger', message: '<strong>API rate limit of GitHub exceeded.</strong> Try again at ' + new Date(headers('X-RateLimit-Reset') * 1000) + '.'}]
		} else if(status=503) {
			$scope.alerts=[{type: 'danger', message: '<strong>Back-end service in unavailable</strong>'}]
		} else {
			$scope.alerts=[{type: 'warning', message: '<strong>Server responsed with an error</strong>, status=' + status + ', message=' + data}]
		}
        console.log('Error data ' + data);
		console.log('Error data.message ' + data.message);
		console.log('Error status ' + status);
    });  
});


app.controller('INTEGRATIONCtrl', function ($scope, $http) {
	$scope.pageHeading='Multi Product Demos'
		$scope.demos = new Array();
	$http.get('rest/demos/multi').success(function (demos) {
    	demos.forEach(function(demo) { $scope.demos.push(demo)});
    }).error(function (data, status,headers) {
		if(status=403 && data.message.match("API rate limit exceeded")) {		
			$scope.alerts=[{type: 'danger', message: '<strong>API rate limit of GitHub exceeded.</strong> Try again at ' + new Date(headers('X-RateLimit-Reset') * 1000) + '.'}]
		} else if(status=503) {
			$scope.alerts=[{type: 'danger', message: '<strong>Back-end service in unavailable</strong>'}]
		} else {
			$scope.alerts=[{type: 'warning', message: '<strong>Server responsed with an error</strong>, status=' + status + ', message=' + data}]
		}
        console.log('Error data ' + data);
		console.log('Error data.message ' + data.message);
		console.log('Error status ' + status);
    }); 
});

app.controller('FUSECtrl', function ($scope, $http) {
	$scope.pageHeading='JBoss Fuse &amp; JBoss Fuse Service Works Demos'
		$scope.demos = new Array();
	$http.get('rest/demos/fuse').success(function (demos) {
    	demos.forEach(function(demo) { $scope.demos.push(demo)});
  }).error(function (data, status,headers) {
		if(status=403 && data.message.match("API rate limit exceeded")) {		
			$scope.alerts=[{type: 'danger', message: '<strong>API rate limit of GitHub exceeded.</strong> Try again at ' + new Date(headers('X-RateLimit-Reset') * 1000) + '.'}]
		} else if(status=503) {
			$scope.alerts=[{type: 'danger', message: '<strong>Back-end service in unavailable</strong>'}]
		} else {
			$scope.alerts=[{type: 'warning', message: '<strong>Server responsed with an error</strong>, status=' + status + ', message=' + data}]
		}
        console.log('Error data ' + data);
		console.log('Error data.message ' + data.message);
		console.log('Error status ' + status);
    });  
});

app.controller('EAPCtrl', function ($scope, $http) {
	$scope.pageHeading='JBoss Enterprise Application Platform Demos'
    $scope.demos = new Array();
	$http.get('rest/demos/eap').success(function (demos) {
    	demos.forEach(function(demo) { $scope.demos.push(demo)});
  }).error(function (data, status,headers) {
		if(status=403 && data.message.match("API rate limit exceeded")) {		
			$scope.alerts=[{type: 'danger', message: '<strong>API rate limit of GitHub exceeded.</strong> Try again at ' + new Date(headers('X-RateLimit-Reset') * 1000) + '.'}]
		} else if(status=503) {
			$scope.alerts=[{type: 'danger', message: '<strong>Back-end service in unavailable</strong>'}]
		} else {
			$scope.alerts=[{type: 'warning', message: '<strong>Server responsed with an error</strong>, status=' + status + ', message=' + data}]
		}
        console.log('Error data ' + data);
		console.log('Error data.message ' + data.message);
		console.log('Error status ' + status);
    });  
});

app.controller('DVCtrl', function ($scope, $http) {
	$scope.pageHeading='JBoss Data Virtualization Demos'
	$scope.demos = new Array();
	$http.get('rest/demos/dv').success(function (demos) {
    	demos.forEach(function(demo) { $scope.demos.push(demo)});
    }).error(function (data, status,headers) {
		if(status=403 && data.message.match("API rate limit exceeded")) {		
			$scope.alerts=[{type: 'danger', message: '<strong>API rate limit of GitHub exceeded.</strong> Try again at ' + new Date(headers('X-RateLimit-Reset') * 1000) + '.'}]
		} else if(status=503) {
			$scope.alerts=[{type: 'danger', message: '<strong>Back-end service in unavailable</strong>'}]
		} else {
			$scope.alerts=[{type: 'warning', message: '<strong>Server responsed with an error</strong>, status=' + status + ', message=' + data}]
		}
        console.log('Error data ' + data);
		console.log('Error data.message ' + data.message);
		console.log('Error status ' + status);
    });
});


    
app.filter('matchString', function () {
  return function (items, searchStr) {
	  return items.filter(function (item) {
		  return item.name.match(searchStr);
	  });
  }
});

app.filter('matchStringOrString', function () {
  return function (items, searchStr1, searchStr2) {
	  return items.filter(function (item) {
		  return item.name.match(searchStr1) || item.name.match(searchStr2);
	  });
  }
});

app.filter('matchStringAndString', function () {
  return function (items, searchStr1, searchStr2) {
	  return items.filter(function (item) {
		  return item.name.match(searchStr1) && item.name.match(searchStr2);
	  });
  }
});
