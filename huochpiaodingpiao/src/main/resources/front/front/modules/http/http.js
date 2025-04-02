
layui.define(['jquery', 'layer'], function(exports) { //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
	"use strict";
	var jquery = layui.jquery,
		layer = layui.layer,
		        // baseurl = "http://localhost:8080/huochpiaodingpiao/";
				baseurl = "http://localhost:8080/huochpiaodingpiao/";
        	var http = {
		        domain : "http://8.129.11.174:8080/huochpiaodingpiao/",
        		baseurl: baseurl,
		/**
		 * 获取传递参数值(修改支持中文)
		 */
		getParam: function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return decodeURI(r[2]); //对参数进行decodeURI解码
			return null;
		},
		request: function(url, type, data, callback) {
			//loading层
			var index = layer.load(1, {
				shade: [0.1, '#fff'] //0.1透明度的白色背景
			});
			url = baseurl + url;
			data['t'] = jquery.now();
			jquery.ajax({
				url: url,
				beforeSend: function(request) {
					request.setRequestHeader("Token", localStorage.getItem("Token"));
				},
				contentType: 'application/x-www-form-urlencoded',
				data: data,
				dataType: 'json',
				type: type,
				success: function(result, status, xhr) {
					if (result.code == 0) {
						callback(result);
					} else if (result.code == 401) {
						window.parent.location.href = '../login/login.html';
					} else {
						layer.msg(result.msg, {
							time: 2000,
							icon: 5
						})
					}
					layer.close(index);
				},
				error: function(xhr, status, error) {
					console.log(xhr, status, error)
					layer.msg("请求接口失败", {
						time: 2000,
						icon: 5
					})
					layer.close(index);
				}
			});
		},
				// 假设 http 是一个自定义的请求对象
// 新增一个处理非 JSON 响应的方法
				requestText : function(url, method, data, callback) {
					var xhr = new XMLHttpRequest();
					xhr.open(method, url = baseurl + url, true);
					xhr.setRequestHeader('Content-Type', 'application/json');
					xhr.onreadystatechange = function() {
						if (xhr.readyState === 4) {
							if (xhr.status === 200) {
								callback(xhr.responseText);
							} else {
								console.error('请求失败，状态码: ', xhr.status);
							}
						}
					};
					xhr.send(JSON.stringify(data));
				},
		requestJson: function(url, type, data, callback) {
			//loading层
			var index = layer.load(1, {
				shade: [0.1, '#fff'] //0.1透明度的白色背景
			});
			url = baseurl + url;
			var params = null;
			data['t'] = jquery.now();
			if (data) {
				params = JSON.stringify(data);
			}
			console.log(url)
			jquery.ajax({
				url: url,
				beforeSend: function(request) {
					request.setRequestHeader("Token", localStorage.getItem("Token"));
				},
				contentType: 'application/json',
				data: params,
				dataType: 'json',
				type: type,
				success: function(result, status, xhr) {
					console.log(result)
					if (result.code == 0) {
						callback(result);
					} else if (result.code == 401) {
						window.parent.location.href = '../login/login.html';
					} else {
						layer.msg(result.msg, {
							time: 2000,
							icon: 5
						})
					}
					layer.close(index);
				},
				error: function(xhr, status, error) {
					console.log(xhr, status, error)
					layer.msg("请求接口失败", {
						time: 2000,
						icon: 5
					})
					layer.close(index);
				},
			});
		},

				requestJsona: function(url, type, data, callback) {
					//loading层
					url = baseurl + url;
					console.log(url)
					jquery.ajax({
						url: url,
						beforeSend: function(request) {
							request.setRequestHeader("Token", localStorage.getItem("Token"));
						},
						contentType: 'application/json',
						dataType: 'json',
						type: type,
						success: function(result, status, xhr) {
							console.log(result)
							if (result.code == 0) {
								callback(result);
							} else if (result.code == 401) {
								window.parent.location.href = '../login/login.html';
							} else {
								layer.msg(result.msg, {
									time: 2000,
									icon: 5
								})
							}
						},
						error: function(xhr, status, error) {
							console.log(xhr, status, error)
							layer.msg("请求接口失败", {
								time: 2000,
								icon: 5
							})
							layer.close(index);
						},
					});
				},
		upload: function(file, fileName, callback) {
			var url = baseurl + "file/upload";
			var formData = new FormData();
			formData.append('file', file);
			formData.append('fileName', fileName);
			jquery.ajax({
				url: url,
				/*接口域名地址*/
				type: 'post',
				data: formData,
				headers: {
					"Token": localStorage.getItem("Token")
				}, //添加请求头部
				contentType: false,
				processData: false,
				success: function(res) {
					if (res.code == 0) {
						callback(res);
					} else if (res.code == 401) {
						window.parent.location.href = '../login/login.html';
					} else {
						layer.msg(res.msg, {
							time: 2000,
							icon: 5
						})
					}
				}
			})
		}
	}
	//输出接口
	exports('http', http);
});
