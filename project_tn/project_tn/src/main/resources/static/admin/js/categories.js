const app = angular.module("app", []);
app.controller("my-crtl",function($scope,$http,$location,$filter){
	$scope.items=[];
	$scope.itemss=[];
	$scope.itemsss=[];
	$scope.form={};
	$scope.form1={};
	$scope.form2={};
	$scope.authoritiesRepo=[];
	$scope.roles=[];
    $scope.selection =[];
    
    $scope.firstRow = [];
	
	//2nd row content
	$scope.secondRowRevenueByDate = [];
	$scope.secondRowRevenueByRevenue = [];
	$scope.secondRowCateName = [];
	$scope.secondRowNumberOfProduct= [];
	//2nd row content edge
	
	//3rd row content
	$scope.thirdRowCateName = [];
	$scope.thirdRowPercentageByCate = [];
	$scope.thirdRowCateName2 = [];
	$scope.thirdRowAvailable = [];
	$scope.thirdRowUnAvailable = [];
	//3rd row content edge
	
	//4th row content
	$scope.fourthRowProductName = [];
	$scope.fourthRowQuantity = []; 
	//4th row content edge
	
	//5th row content
	$scope.fifRowContent = [];
	
	$scope.initialiaze=function(){
		//hiển thị danh sách quản lý hãng sp
		$http.get(`/categories/list`).then(resp =>{
			$scope.items=resp.data;
		});
		//hiển thị danh sách quản lý tài khoản
		$http.get(`/users/list`).then(resp =>{
			$scope.itemss=resp.data;
		});
	    //hiển thị danh sách roles
		$http.get(`/roles/list`).then(resp =>{
			$scope.roles=resp.data;
		});
			//load authorities of staffs and directors
		$http.get("/authorities/list?admin=true").then(resp=>{
			$scope.authorities = resp.data;
		})
		
		$http.get("/product/list").then(resp=>{
			$scope.itemsss = resp.data;
		})
		
		$http.get("/admin/oneshop/statistical/secondRow").then(resp=>{
			//revenue last 7days
		//	resp.data.revenueLast7Days.forEach(e=>{
			//	$scope.secondRowRevenueByDate.push($filter('date')(e[0],'dd-MM-yyyy'));
			//	$scope.secondRowRevenueByRevenue.push(e[1]);
			//})
			
				resp.data.revenueLast7Days.forEach(e=>{
				$scope.secondRowRevenueByDate.push($filter('date')(e[0],'dd-MM'));
				$scope.secondRowRevenueByRevenue.push(e[1]);
			})
			//product sold by categories
			resp.data.productSoldByCate.forEach(e=>{
				$scope.secondRowCateName.push(e[0]);
				$scope.secondRowNumberOfProduct.push(e[1]);
			})
			//line chart for Revenue last 7 days
			var dateListRevenue = $scope.secondRowRevenueByDate
			var revenueListByDays = $scope.secondRowRevenueByRevenue
			var lineRevenueChart = document.getElementById('myLineChartRevenue').getContext('2d');
			var myChart = new Chart(lineRevenueChart, {
		        type: 'line',
		        data: {
		            labels: dateListRevenue,
		            datasets: [{
		                // label: 'User By Roles',
		                data: revenueListByDays,
		                backgroundColor: [
		                'rgb(54, 162, 235,0.7)',//blue
		                'rgb(201, 203, 207,0.7)',//gray
		                'rgb(255, 205, 86,0.7)',//yellow
		                'rgb(75, 192, 192)',//green
		                'rgb(255, 99, 132)',//red
		                ],
		                borderColor: ['rgb(54, 162, 235,1)'],//green
		                fill: true,
		                lineTension:0,
		                borderWidth: 3
		            }]
		        },
		        options: {
		            indexAxis: 'x',
		            scales: {
		
		            },
		            responsive: true,
		            plugins: {
		                title: {
		                    display: false,
		                    // text: 'Number of Registered Users By Roles',
		                    padding: {
		                        bottom: 30,
		                    },
		                    font:{
		                        size:20
		                    }
		                },
		                legend: {
		                display:false
		                },
					
			        },
		        }
		    });
		    //bar chart for category by number of product sold
		    var productTypeList = $scope.secondRowCateName;
		    var numberSoldByType = $scope.secondRowNumberOfProduct;
		    var barChartSoldByType = document.getElementById('myBarChartSoldByType').getContext('2d');
		    var gradient = barChartSoldByType.createLinearGradient(0, 0, 600, 0);
		    	gradient.addColorStop(0, 'rgba(159 182 205)');
		        gradient.addColorStop(1, 'rgba(205 133 63)');   
		    var myChart = new Chart(barChartSoldByType, {
		        type: 'bar',
		        data: {
		            labels: productTypeList,
		            datasets: [{
		                // label: '# of Votes',
		                data: numberSoldByType,
		                // backgroundColor: colorArrays1,
		                // borderColor: colorArrays1,
		
		                backgroundColor  : gradient,
		                strokeColor : "#FCA654",
		                pointColor : "#FCA654",
		                pointStrokeColor : "#FCA654",
		                pointHighlightFill: "#FCA654",
		                pointHighlightStroke: "#FCA654",
		
		                borderWidth: 1
		            }]
		        },
		        options: {
		            indexAxis: 'y',
		            scales: {
		                y: {
		                    beginAtZero: true
		                }
		            },
		            responsive: true,
		            plugins: {
		                title: {
		                    display: false,
		                    text: 'Payment status last 7 days',
		                    padding: {
		                        bottom: 10,
		                    },
		                    font:{
		                        size:20
		                    }
		                },
		                legend: {
		                    display:false,
		                },
					
			        },
		        }
		    });
		})
		$scope.reset1();
			$scope.reset2();
			$scope.reset();
	}
	
		//Chọn roles
	$scope.toggleRole = function(role){
		var compareElement = -1;
		var idx = $scope.selection.indexOf(role);
		console.log(idx);
		//Currently Selected
		if(idx>-1){
			$scope.selection.splice(idx,1);
		}
		//Is newly added
		else{
			$scope.selection.push(role);
		}
	}
	
	$scope.getOneByRole = function(username){
		$http.get(`/authorities/authoritiesOne?username=${username}`).then(resp=>{
			$scope.selection = [];
			$scope.roles.forEach(e=>{
				resp.data.forEach(e1=>{
					if(e.name == e1.roles.name){
						$scope.selection.push(e);
					}
				})
			})
		})
	}
	
	//Hiển thị lên form
    $scope.edit1 = function(item){
		$scope.form1 = angular.copy(item);
		$scope.getOneByRole(item.username);
		$(".nav-tabs a:eq(0)").tab('show');
		$('#update1').prop('disabled',false);
			$('#create1').prop('disabled',true);

			$('#exampleInputName').prop('disabled',true);
			
			$('#createEdit').click(function(){
	$('html,body').animate({
		scrollTop:$('#form').offset().top
		},1000);
})
		
    }
	//Thêm account
    $scope.create1 = function(){
		var item = angular.copy($scope.form1);
		$http.post(`/users/list/create`,item).then(resp=>{
			$scope.itemss.push(resp.data);
			console.log(resp.data);
			//thêm phân quyền
			$scope.selection.forEach(r=>{
				var authority = {users:item,roles:r};
				$http.post(`/authorities/list`,authority).then(resp=>{
					$scope.itemss.push(resp.data);
				}).catch(err=>{
					console.log("Error ",err);
				})
			})
		
			alert("Thêm tài khoản thành công!");
			$(".nav-tabs a:eq(1)").tab('show');

		}).catch(err=>{
			console.log("Error ",err);
			alert("Thêm tài khoản thất bại!");
		})
    }
	
	 //Upload Hình
    $scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/avatar',data,{
			transformRequest:angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp=>{
			$scope.form1.imgUrl = resp.data.name;
		}).catch(err=>{
			alert('Lỗi upload Ảnh');
			console.log("Error ",err)
		})
    }
	
	   //Update account
    $scope.update1 = function(){
		var item = angular.copy($scope.form1);
		$http.put(`/users/list/${item.username}`,item).then(resp=>{
			var index = $scope.itemss.findIndex(p=>p.username == item.username);
			$scope.itemss[index] = item;
			
			//xoá toàn bộ roles của user hiện tại
			$http.delete(`/authorities/authoritiesOne/${item.username}`).then(resp=>{
			//sau khi xoá thì thêm mới lại role đã chọn
			
				$scope.selection.forEach(r=>{
					var authority = {users:item,roles:r};
					$http.post(`/authorities/list`,authority).then(resp=>{
						$scope.itemss.push(resp.data);
					}).catch(err=>{
						console.log("Error ",err);
					})
				})
			})
			console.log(resp.data);
			alert('Cập nhật tài khoản thành công!');
	
		}).catch(err=>{
			alert('Lỗi cập nhật tài khoản!')
			console.log("Error ",err);
		})
		
		
	}
	
	//Hiển thị lên form
    $scope.edit2 = function(item){
		$scope.form2 = angular.copy(item);
	
		$(".nav-tabs a:eq(0)").tab('show');
		$('#update2').prop('disabled',false);
			$('#create2').prop('disabled',true);
		
    }

//Thêm product
    $scope.create2 = function(){
		var item = angular.copy($scope.form2);
		$http.post(`/product/list`,item).then(resp=>{
			$scope.itemsss.push(resp.data);
			console.log(resp.data);
			alert("Thêm sản phẩm thành công!");
			$(".nav-tabs a:eq(1)").tab('show');

		}).catch(err=>{
			console.log("Error ",err);
			alert("Thêm sản phẩm thất bại!");
		})
    }

   //Update product
    $scope.update2 = function(){
		var item = angular.copy($scope.form2);
		$http.put(`/product/list/${item.username}`,item).then(resp=>{
			var index = $scope.itemsss.findIndex(p=>p.id == item.id);
			$scope.itemsss[index] = item;
			
		
			alert('Cập nhật tài khoản thành công!');
			console.log(resp.data);
			$(".nav-tabs a:eq(0)").tab('show');
		}).catch(err=>{
			alert('Lỗi cập nhật tài khoản!')
			console.log("Error ",err);
		})
		
		
	}
	
	 $scope.pagers2 = {
		page:0,
		size:10,
		get itemsss(){
			var start = this.page * this.size;
			return	$scope.itemsss.slice(start,start+this.size);
		},
		get count(){
			return Math.ceil(1.0*$scope.itemsss.length/this.size);
		},
		first(){
			this.page = 0;
		},
		previous(){
			this.page--;
			if(this.page<0){
				this.last();
			};
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			};
		},
		last(){
			this.page = this.count -1;
		},
	}
	
	 //Upload Hình
    $scope.imageChanged2 = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/avatar',data,{
			transformRequest:angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp=>{
			$scope.form2.imgUrl = resp.data.name;
		}).catch(err=>{
			alert('Lỗi upload Ảnh');
			console.log("Error ",err)
		})
    }
	
	
		$scope.reset1 = function(){
		$scope.form1 = {
			imgUrl:'45c9f6a9.jpg',
		}
		$('#create1').prop('disabled',false);
		$('#update1').prop('disabled',true);
	$('#exampleInputName').prop('disabled',false);
    }
	
	$scope.reset2 = function(){
		$scope.form2 = {
			imgUrl:'samsung-galaxy-a8-plus-2018-gold-600x600.jpg',
		}
		$('#create2').prop('disabled',false);
		
	}
	//load sản phẩm lên form
	$scope.edit=function(p){
			$scope.form=angular.copy(p);
			$(".nav-tabs a:eq(0)").tab('show');
			$('#update').prop('disabled',false);
			$('#create').prop('disabled',true);
		
		
	}
	 $scope.pagers = {
		page:0,
		size:10,
		get itemss(){
			var start = this.page * this.size;
			return	$scope.itemss.slice(start,start+this.size);
		},
		get count(){
			return Math.ceil(1.0*$scope.itemss.length/this.size);
		},
		first(){
			this.page = 0;
		},
		previous(){
			this.page--;
			if(this.page<0){
				this.last();
			};
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			};
		},
		last(){
			this.page = this.count -1;
		},
	}

	//Thêm hãng sản phẩm
	$scope.create= function(){
		var item=angular.copy($scope.form);
		$http.post(`/categories/list`,item).then(resp =>{
			$scope.items.push(resp.data);
			alert("Thêm mới thành công")
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error =>{
			alert("Thêm mới thất bại.Vui lòng kiểm tra và nhập  lại thông tin");
			console.log("Error",error);
		});
	}
	//cập nhật hãng sản phẩm
	$scope.update= function() {
		var item=angular.copy($scope.form);
		$http.put(`/categories/list/${item.id}`,item).then(resp =>{
			var index=$scope.items.findIndex(p => p.id == item.id);
			$scope.items[index]=item;
			alert("Cập nhật thành công");
			console.log(resp.data);
			$scope.initialiaze();
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error =>{
			alert("Cập nhật thất bại.Vui lòng kiểm tra và nhập  lại thông tin");
			console.log("Error",error);
		});
	}
	//xóa hãng sản phẩm
	$scope.delete=function(p){
		$http.delete(`/categories/list/${p.id}`).then(resp =>{
			var index=$scope.items.findIndex(p => p.id == p.id);
			$scope.items.splice(index,1);
			alert("Xóa thành công")
			console.log(resp.data);
			$scope.reset();
			$scope.initialiaze();
		});
	}
	//reset
	$scope.reset= function(){
		$scope.form={}
		$('#create').prop('disabled',false);
		$('#update').prop('disabled',true);
	}
	 $scope.pager = {
		page:0,
		size:5,
		get items(){
			var start = this.page * this.size;
			return	$scope.items.slice(start,start+this.size);
		},
		get count(){
			return Math.ceil(1.0*$scope.items.length/this.size);
		},
		first(){
			this.page = 0;
		},
		previous(){
			this.page--;
			if(this.page<0){
				this.last();
			};
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			};
		},
		last(){
			this.page = this.count -1;
		},
	}
	$scope.initialiaze();
})

 