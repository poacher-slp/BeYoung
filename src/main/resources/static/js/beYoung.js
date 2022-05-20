//用户名，密码，手机号码，邮箱检验
function check() {
    let username = $("#username").val();
    let password = $("#password").val();
    let passwordAgain = $("#passwordAgain").val();
    let userMobile = $("#userMobile").val();
    let userEmail = $("#userEmail").val();
    let oldPassword = $("#oldPassword").val();
    console.log(oldPassword);
    let usernameReg = /^[a-zA-Z0-9]{3,16}$/;
    let passwordReg = /^[a-zA-Z0-9]{6,18}$/;
    let phoneReg = /^1[3578]+\d{9}$/;
    let emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    console.log(passwordAgain);
    if((username == "")
        || (password == "")
        || (passwordAgain == "")
        || (userMobile == "")
        || (userEmail == "")
        || (oldPassword == "")) {
        alert("请将信息填写完整");
        return false;
    }
    else if(!usernameReg.test(username)) {
        alert("用户名以数字，字母开头的3-16个字符");
        return false;
    }
    else if(!passwordReg.test(password)) {
        alert("密码以数字，字母开头的至少6个字符");
        return false;
    }
    else if(typeof(passwordAgain) != "undefined" && password != passwordAgain) {
        alert("两次密码输入不一致");
        return false;
    }
    else if(typeof(userMobile) != "undefined" && !phoneReg.test(userMobile)) {
        console.log(userMobile);
        alert("手机号码输入错误");
        return false;
    }
    else if(typeof(userEmail) != "undefined" && !emailReg.test(userEmail)) {
        alert("邮箱格式输入有误");
        return false;
    }
    else if(password == oldPassword) {
        alert("新旧密码相同，请重新输入新密码。");
        return false;
    }
    else
        return true;
};

//注册页的注册
$("#btn-reg").click(function() {
    // test();
    let flag = check();
    console.log(flag);
    if(!flag) {
        console.log("check()值为false");
        return ;
    }else {
        console.log("开始注册用户");
        $.ajax({
            url: "/users/reg",
            type: "post",
            data: $("#form-reg").serialize(),
            dataType: "json",
            success: function (json) {
                if (json.state == 200) {
                    alert("注册成功！");
                } else {
                    alert("注册失败！" + json.message);
                }
            },
            error: function (xhr) {
                alert("注册时产生未知的错误！" + xhr.status);
            }
        });
    }
});

//登录页的登录
$("#btn-login").click(function(){
    console.log($("#form-login").serialize());
    let flag = check();
    console.log("check()值：" + flag);
    if(!flag) {
        return ;
    }else {
        $.ajax({
            url: "/users/login",
            type: "post",
            data: $("#form-login").serialize(),
            dataType: "json",
            success: function(json){
                if(json.state == 200) {
                    $.cookie("userImg", json.data.userImg, {expires: 7});
                    sessionStorage.setItem("nickname", json.data.nickname);
                    if(json.data.role == 1) {
                        location.href = "index.html";
                    }
                    if(json.data.role == 0) {
                        location.href = "adminIndex.html";
                    }
                }
                else {
                    alert("登录失败！"+ json.message);
                }
            },
            error: function(xhr){
                alert("登录时产生未知的异常!" + xhr.message);
            }
        });
    }
});

//登录和用户名的替换
function welcomeUser() {
    let name = sessionStorage.getItem("nickname");
    if(name != null) {
        console.log("昵称："+name);
        let str = '<a onclick="logout();">【'+name+'】注销</a>';
        $("#welcome").html(str);
    }
    else {
        console.log("游客访问");
        let str = '<a href="login.html"><span class="fa fa-user"></span>&nbsp;登录</a>';
        $("#welcome").html(str);
    }
};

//注销
function logout() {
    $.ajax({
        url: "/users/logout",
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                console.log("注销成功");
                sessionStorage.removeItem("nickname");
                location.href = "index.html";
            }
            else {
                alert("注销失败！" + json.message);
            }
        },
        error: function(xhr){
            alert("注销时产生未知的异常" + xhr.status);
        }
    });
};

//显示热销商品
function showHotList() {
    $("#hot-list").empty();
    $.ajax({
        url: "/products/hotlist",
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                console.log("获取热销化妆品成功");
                let list = json.data;
                for(let i = 0; i < list.length; i++) {
                    let html = '<div class="col-md-12">\n' +
                        '<div class="col-md-7 text-row-2"><a href="product.html?id=#{id}">#{title}</a></div>\n' +
                        '<div class="col-md-2">￥#{price}</div>\n' +
                        '<div class="col-md-3"><img src="../images/#{image}" class="img-responsive" /></div>\n' +
                        '</div>';
                    html = html.replace(/#{id}/g,list[i].id);
                    html = html.replace(/#{title}/g,list[i].title);
                    html = html.replace(/#{price}/g,list[i].price);
                    html = html.replace(/#{image}/g,list[i].image);
                    $("#hot-list").append(html);
                }
            }
            else {
                alert("获取热销化妆品失败!" + json.message);
            }
        },
        error: function(xhr){
            alert("获取热销化妆品时产生未知的异常!" + xhr.status);
        }
    });
};

//修改密码
$("#btn-change-password").click(function(){
    if (check()) {
        $.ajax({
            url: "/users/changePwd",
            type: "post",
            data: $("#form-change-password").serialize(),
            dataType: "json",
            success: function(json){
                if(json.state == 200) {
                    alert("密码修改成功，请重新登陆!");
                    logout();
                    location.href = "login.html";
                }
                else {
                    alert("密码修改失败!" + json.message);
                }
            },
            error: function(xhr){
                alert("修改密码时产生未知的异常!" + xhr.message);
            }
        });
    }
});

//上传头像
$("#btn-changeImg").click(function(){
    $.ajax({
        url: "/users/changeImg",
        type: "post",
        data: new FormData($("#form-changeImg")[0]),
        processData: false,	//处理数据的形式，关闭处理数据
        contentType: false,	//提交数据形式，关闭默认提交数据形式
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                alert("头像修改成功");
                $("#img-avatar").attr("src", "../images/" + json.data);
                $.cookie("userImg", json.data, {expires: 7});
                location.href = "upload.html";
            }
            else {
                alert("头像修改失败");
            }
        },
        error: function(xhr){
            alert("头像修改时产生未知的异常" + xhr.message);
        }
    });
});

//获取个人信息
function getInfo() {
    $.ajax({
        url: "/users/getByUid",
        type: "get",
        // data: $("#form-change-info").serialize(),
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                $("#username").val(json.data.username);
                $("#nickname").val(json.data.nickname);
                $("#userMobile").val(json.data.userMobile);
                $("#userEmail").val(json.data.userEmail);
                let radio = json.data.userSex == 0 ? $("#gender-female") : $("#gender-male");
                radio.prop("checked", "checked");
            }
            else {
                alert("用户的数据不存在");
            }
        },
        error: function(xhr){
            alert("查询用户信息产生未知的异常" + xhr.status);
        }
    });
};

//修改个人资料
$("#btn-change-info").click(function(){
    $.ajax({
        url: "/users/changeInfo",
        type: "post",
        data: $("#form-change-info").serialize(),
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                alert("个人资料修改成功");
                sessionStorage.setItem("nickname", $("#nickname").val());
                location.href = "userdata.html";
            }
            else {
                alert("个人资料修改失败");
            }
        },
        error: function(xhr){
            alert("个人资料修改时产生未知的异常" + xhr.message);
        }
    });
});

//获取收货地址列表
function showAddressList() {
    $("#address-list").empty();
    $.ajax({
        url: "/addresses",
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                let list = json.data;
                for(let i = 0; i < list.length; i++) {
                    let str = '<tr>\n' +
                        '<td>#{tag}</td>\n' +
                        '<td>#{receiverName}</td>\n' +
                        '<td>#{addr}</td>\n' +
                        '<td>#{receiverMobile}</td>\n' +
                        '<td><a class="btn btn-xs btn-info" href="addAddress.html?aid=#{aid}"><span class="fa fa-edit"></span> 修改</a></td>\n' +
                        '<td><a class="btn btn-xs add-del btn-info" onclick="deleteByAid(#{aid})"><span class="fa fa-trash-o"></span> 删除</a></td>\n' +
                        '<td><a class="btn btn-xs add-def btn-default" id="a-default" onclick="defaultAddr(#{aid})">设为默认</a></td>\n' +
                        '</tr>';
                    str = str.replace(/#{tag}/g,list[i].tag);
                    str = str.replace(/#{receiverName}/g,list[i].receiverName);
                    str = str.replace(/#{addr}/g,list[i].addr);
                    str = str.replace(/#{receiverMobile}/g,list[i].receiverMobile);
                    str = str.replace(/#{aid}/g,list[i].addrId);
                    $("#address-list").append(str);
                }
                $(".add-def:eq(0)").hide();
            }
            else {
                alert("用户收获地址加载失败");
            }
        },
        error: function(xhr){
            alert("新增收货地址时产生未知的异常" + xhr.status);
        }
    });
};

//根据aid获取收货地址数据
function getAddrByAid(aid,value) {
    $.ajax({
        url: "/addresses/getAddr",
        type: "get",
        data: {aid:value},
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                $("#receiverName").val(json.data.receiverName);
                $("#province-list").val(json.data.provinceCode);
                $("#city-list").val(json.data.cityCode);
                $("#area-list").val(json.data.areaCode);
                $("#addr").val(json.data.addr);
                $("#receiverMobile").val(json.data.receiverMobile);
                $("#postCode").val(json.data.postCode);
                $("#tag").val(json.data.tag);
            }
            else {
                alert("获取地址加载失败！" + json.message);
            }
        },
        error: function(xhr){
            alert("获取地址时产生未知的异常!" + xhr.status);
        }
    });
};

//新增&修改
$("#btn-add-new-address").click(function(){
    console.log(location.search.substr(1));
    let aid = location.search.substr(1).split("=")[0];
    let value = location.search.substr(1).split("=")[1];
    if(!aid) {
        console.log($("#form-add-new-address").serialize());
        addAddr();
    }
    else {
        console.log($("#form-add-new-address").serialize());
        updateByAid(value);
    }
});

//新增收货地址
function addAddr() {
    $.ajax({
        url: "/addresses/addNewAddr",
        type: "post",
        data: $("#form-add-new-address").serialize(),
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                alert("新增收货地址成功");
                location.href = "address.html";
            }
            else {
                alert("新增收货地址失败。" + json.message);
            }
        },
        error: function(xhr){
            alert("新增收货地址时产生未知的异常!" + xhr.status);
        }
    });
};

//修改收货地址
function updateByAid(value) {
    $.ajax({
        url: "/addresses/"+value+"/update",
        type: "post",
        data: $("#form-add-new-address").serialize(),
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                alert("修改地址成功");
                location.href = "address.html";
            }
            else {
                alert("修改收获地址失败！" + json.message);
            }
        },
        error: function(xhr){
            alert("修改收获地址时产生未知的异常!" + xhr.status);
        }
    });
};

//删除收货地址
function deleteByAid(aid){
    if(confirm("确定删除该地址吗")) {
        $.ajax({
            url: "/addresses/"+aid+"/delete",
            type: "post",
            dataType: "json",
            success: function(json){
                if(json.state == 200) {
                    showAddressList();
                }
                else {
                    alert("删除收获地址失败!" + json.message);
                }
            },
            error: function(xhr){
                alert("删除收获地址时产生未知的异常!" + xhr.status);
            }
        });
    }
};

//设为默认地址
function defaultAddr(aid) {
    console.log("设为默认");
    $.ajax({
        url: "/addresses/"+aid+"/setDefault",
        type: "post",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                showAddressList();
            }
            else {
                alert("设置默认地址失败!" + json.message);
            }
        },
        error: function(xhr){
            alert("设置默认地址时产生未知的异常" + xhr.status);
        }
    });
};

//商品详情
function productDetail(id) {
    $.ajax({
        url: "/products/"+id+"/details",
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                $("#product-title").html(json.data.title);
                $("#product-sell-point").html(json.data.sellPoint);
                $("#product-price").html(json.data.price);
                for(let i = 0; i <= 5; i++) {
                    $("#product-image-1-big").attr("src", "../images/" + json.data.image);
                    // $("#product-image-" + i).attr("src", ".." + json.data.image + i + ".jpg");
                }
            }
            else {
                alert("获取化妆品信息失败！" + json.message);
                location.href = "index.html";
            }
        },
        error: function(xhr){
            alert("获取商品信息出现未知的异常！" + xhr.status);
        }
    });
};

$("#btn-add-to-cart").click(function () {
    let id = $.getUrlParam("id");
    let amount = $("#num").val();
    addToCart(id, amount);
});

//加入购物车
function addToCart(id, amount) {
    $.ajax({
        url: "/carts/addToCart",
        type: "post",
        data: {
            "pid": id,
            "amount": amount
        },
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                alert("成功加入购物车");
                location.href = "cart.html";
            }
            else {
                alert("加入购物车时失败！" + json.message);
            }
        },
        error: function(xhr){
            alert("请先登录账号!" + xhr.status);
        }
    });
};

//显示购物车列表
function showCartList() {
    $("#cart-list").empty();
    $.ajax({
        url: "/carts",
        type: "get",
        dataType: "json",
        success: function (json) {
            let list = json.data;
            for(let i = 0; i < list.length; i++) {
                let tr = '<tr>\n' +
                    '<td>\n' +
                    '<input value="#{cid}" name="cids" type="checkbox" class="ckitem" />\n' +
                    '</td>\n' +
                    '<td><img src="../images/#{image}" class="img-responsive" /></td>\n' +
                    '<td>#{title}#{msg}</td>\n' +
                    '<td>¥<span id="goodsPrice#{cid}">#{price}</span></td>\n' +
                    '<td>\n' +
                    '<input id="price-#{cid}" type="button" value="-" class="num-btn" onclick="reduceNum(#{cid})" />\n' +
                    '<input id="goodsCount#{cid}" type="text" size="2" readonly="readonly" class="num-text" value="#{num}">\n' +
                    '<input id="price+#{cid}" class="num-btn" type="button" value="+" onclick="addNum(#{cid})" />\n' +
                    '</td>\n' +
                    '<td><span id="goodsCast#{cid}">#{totalPrice}</span></td>\n' +
                    '<td>\n' +
                    '<input type="button" onclick="delCartItem(#{cid})" class="cart-del btn btn-default btn-xs" value="删除" />\n' +
                    '</td>\n' +
                    '</tr>';
                tr = tr.replace(/#{cid}/g, list[i].cid);
                tr = tr.replace(/#{image}/g, list[i].image);
                tr = tr.replace(/#{price}/g, list[i].price);
                tr = tr.replace(/#{title}/g, list[i].title);
                tr = tr.replace(/#{msg}/g, list[i].realPrice);
                tr = tr.replace(/#{num}/g, list[i].num);
                tr = tr.replace(/#{totalPrice}/g, list[i].num * list[i].price);
                $("#cart-list").append(tr);
            }
        },
        error: function (xhr) {
            alert("购物车列表数据加载产生未知的异常：" + xhr.status);
        },
    });
};

//购物车中数量+1
function addNum(cid) {
    $.ajax({
        url: "/carts/" + cid + "/num/add",
        type: "post",
        dataType: "json",
        success: function (json) {
            if(json.state == 200) {
                $("#goodsCount" + cid).val(json.data);
                let price = $("#goodsPrice" + cid).html();
                let totalPrice = price * json.data;
                $("#goodsCast" + cid).html(totalPrice);
            }
            else {
                alert("增加购物车数据失败！" + json.message)
            }
        },
        error: function (xhr) {
            alert("增加购物车商品数量产生未知的异常：" + xhr.status);
        },
    });
};

//购物车中数量-1
function reduceNum(cid) {
    $.ajax({
        url: "/carts/" + cid + "/num/reduce",
        type: "post",
        dataType: "json",
        success: function (json) {
            if(json.state == 200) {
                $("#goodsCount" + cid).val(json.data);
                let price = $("#goodsPrice" + cid).html();
                let totalPrice = price * json.data;
                $("#goodsCast" + cid).html(totalPrice);
            }
            else {
                alert("减少购物车数量失败！" + json.message)
            }
        },
        error: function (xhr) {
            alert("减少购物车商品数量产生未知的异常：" + xhr.status);
        },
    });
};
//删除购物车
function delCartItem(cid) {
    if (confirm("确定删除此商品吗？")) {
        $.ajax({
            url: "/carts/"+cid+"/delete",
            type: "post",
            dataType: "json",
            success: function(json){
                if(json.state == 200) {
                    showCartList();
                }
                else {
                    alert("删除该化妆品失败!" + json.message);
                }
            },
            error: function(xhr){
                alert("删除该化妆品时产生未知的异常!" + xhr.status);
            }
        });
    }
}

//获取化妆品列表
function showProductList() {
    $("#product-list").empty();
    $.ajax({
        url: "/products",
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                console.log("获取化妆品列表成功");
                let list = json.data;
                for(let i = 0; i < list.length; i++) {
                    let html = '<tr>\n' +
                        '<td>#{itemType}</td>\n' +
                        '<td>#{title}</td>\n' +
                        '<td>￥#{price}</td>\n' +
                        '<td>#{num}</td>\n' +
                        '<td><a class="btn btn-xs btn-info" href="editProduct.html?id=#{id}"><span class="fa fa-edit"></span> 修改</a></td>\n' +
                        '<td>';
                    if(list[i].status != 2) {
                        html += '<a class="btn btn-xs add-del btn-info" onclick="deleteProduct(#{id})"><span class="fa fa-trash-o"></span> 删除</a>';
                    }
                    html += '</td></tr>';
                    html = html.replace(/#{itemType}/g,list[i].itemType);
                    html = html.replace(/#{title}/g,list[i].title);
                    html = html.replace(/#{price}/g,list[i].price);
                    html = html.replace(/#{num}/g,list[i].num);
                    html = html.replace(/#{id}/g,list[i].id);
                    $("#product-list").append(html);
                }
            }
            else {
                alert("获取化妆品列表失败!" + json.message);
            }
        },
        error: function(xhr){
            alert("获取化妆品列表时产生未知的异常!" + xhr.status);
        }
    });
};

//根据id获取化妆品信息
function getProductByid(value) {
    $.ajax({
        url: "/products/" + value + "/details",
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                $("#itemType").val(json.data.itemType);
                $("#title").val(json.data.title);
                $("#sellPoint").val(json.data.sellPoint);
                $("#price").val(json.data.price);
                $("#num").val(json.data.num);
                $("#priority").val(json.data.priority);
                $("#image").val(json.data.image);
                let radio = json.data.status == 1 ? $("#onSell") : $("#soldOut");
                radio.prop("checked", "checked");
            }
            else {
                alert("获取化妆品信息失败！" + json.message);
            }
        },
        error: function(xhr){
            alert("获取地址时产生未知的异常!" + xhr.status);
        }
    });
};

//新增&修改
$("#btn-product-info").click(function(){
    console.log(location.search.substr(1));
    let id = location.search.substr(1).split("=")[0];
    let value = location.search.substr(1).split("=")[1];
    if(!id) {
        console.log($("#form-product-info").serialize());
        addProduct();
    }
    else {
        console.log($("#form-product-info").serialize());
        updateProductById(value);
    }
});

//新增化妆品
function addProduct() {
    let formData = new FormData($("#form-product-info")[0]);
    console.log(formData);
    $.ajax({
        url: "/products/add",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                alert("新增化妆品成功");
                location.href = "adminIndex.html";
            }
            else {
                alert("新增化妆品失败。" + json.message);
            }
        },
        error: function(xhr){
            alert("新增化妆品时产生未知的异常!" + xhr.status);
        }
    });
};

//修改化妆品信息
function updateProductById(value) {
    let formData = new FormData($("#form-product-info")[0]);
    console.log(formData);
    $.ajax({
        url: "/products/"+value+"/update",
        type: "post",
        // data: $("#form-product-info").serialize(),
        data: formData,
        processData: false,
        contentType: false,
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                alert("修改化妆品成功");
                location.href = "adminIndex.html";
            }
            else {
                alert("修改化妆品失败！" + json.message);
            }
        },
        error: function(xhr){
            alert("修改化妆品时产生未知的异常!" + xhr.status);
        }
    });
};

//删除化妆品
function deleteProduct(id){
    if (confirm("确定下架此商品吗")) {
        $.ajax({
            url: "/products/"+id+"/delete",
            type: "post",
            dataType: "json",
            success: function(json){
                if(json.state == 200) {
                    showProductList();
                }
                else {
                    alert("删除化妆品失败!" + json.message);
                }
            },
            error: function(xhr){
                alert("删除化妆品时产生未知的异常!" + xhr.status);
            }
        });
    }
};

$("#btn-search").click(function () {
    var search = $("#search").val();
    location.href = "search.html?search=" + search;
});

//后台查询
function adminSearch(search) {
    console.log("传后台search:" + search);
    $.ajax({
        url: "/products/search/"+search,
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                console.log("跳过去了");
                $("#product-list").empty();
                console.log("清空了");
                let list = json.data;
                for(let i = 0; i < list.length; i++) {
                    let html = '<tr>\n' +
                        '<td>#{itemType}</td>\n' +
                        '<td>#{title}</td>\n' +
                        '<td>￥#{price}</td>\n' +
                        '<td>#{num}</td>\n' +
                        '<td><a class="btn btn-xs btn-info" href="editProduct.html?id=#{id}"><span class="fa fa-edit"></span> 修改</a></td>\n' +
                        '<td><a class="btn btn-xs add-del btn-info" onclick="deleteProduct(#{id})"><span class="fa fa-trash-o"></span> 删除</a></td>\n' +
                        '</tr>';
                    html = html.replace(/#{itemType}/g,list[i].itemType);
                    html = html.replace(/#{title}/g,list[i].title);
                    html = html.replace(/#{price}/g,list[i].price);
                    html = html.replace(/#{num}/g,list[i].num);
                    html = html.replace(/#{id}/g,list[i].id);
                    $("#product-list").append(html);
                }
            }
            else {
                alert("查询失败！"+ json.message);
            }
        },
        error: function(xhr){
            alert("查询时产生未知的异常!" + xhr.message);
        }
    });
}

//用户查询
function search(search) {
    $.ajax({
        url: "/products/search/"+search,
        // url: "/products/search",
        type: "get",
        // data:{"search":search},
        dataType: "json",
        success: function(json){
            // alert("success"+search);
            if(json.state == 200) {
                // $("#product-list").empty();
                let list = json.data;
                for(let i = 1; i <= (list.length / 4 + 1); i++) {
                    let tr = '<div class="col-md-offset-1 col-md-10">';
                    for(let j = 4 * (i-1); j <= (4 * i - 1) && j < list.length; j++) {
                        let td = '<div class="col-md-3">\n' +
                            '<div class="goods-panel">\n' +
                            '<img src="../images/#{image}" class="img-responsive" />\n' +
                            '<p>¥#{price}</p>\n' +
                            '<p class="text-row-3"><a href="product.html?id=#{id}"><small>#{itemType} #{title}</small></a></p>\n' +
                            '<span>\n' +
                            '<a href="javascript:void(0)" class="btn btn-default btn-xs add-fav"><span class="fa fa-heart-o"></span>加入收藏</a>\n' +
                            '<a onclick="addToCart(#{id},1)" class="btn btn-default btn-xs add-cart"><span class="fa fa-cart-arrow-down"></span>加入购物车</a>\n' +
                            '</span>\n' +
                            '</div>\n' +
                            '</div>';
                        td = td.replace(/#{itemType}/g,list[j].itemType);
                        td = td.replace(/#{title}/g,list[j].title);
                        td = td.replace(/#{price}/g,list[j].price);
                        td = td.replace(/#{image}/g,list[j].image);
                        td = td.replace(/#{id}/g,list[j].id);
                        tr += td;
                    }
                    tr += "</div>";
                    $("#searchResult").append(tr);
                }
            }
            else {
                alert("搜索失败！"+ json.message);
            }
        },
        error: function(xhr){
            // alert("error"+search);
            alert("搜索时产生未知的异常!" + xhr.message);
        }
    });
}

//显示所有商品
function showList() {
    $.ajax({
        url: "/products",
        type: "get",
        dataType: "json",
        success: function(json){
            if(json.state == 200) {
                // $("#product-list").empty();
                let list = json.data;
                for(let i = 1; i <= (list.length / 4 + 1); i++) {
                    let tr = '<div class="col-md-offset-1 col-md-10">';
                    for(let j = 4 * (i-1); j <= (4 * i - 1) && j < list.length; j++) {
                        let td = '<div class="col-md-3">\n' +
                            '<div class="goods-panel">\n' +
                            '<img src="../images/#{image}" class="img-responsive" />\n';

                            if (list[j].priority == null) {
                                td += '<p>¥#{price} 累计销售: 0</p>\n';
                            }
                            else {
                                td += '<p>¥#{price} 累计销售: #{priority}</p>\n';
                            }

                            td += '<p class="text-row-3"><a href="product.html?id=#{id}"><small>#{itemType} #{title}</small></a></p>\n' +
                            '<span>\n' +
                            '<a href="javascript:void(0)" class="btn btn-default btn-xs add-fav"><span class="fa fa-heart-o"></span>加入收藏</a>\n' +
                            '<a onclick="addToCart(#{id},1)" class="btn btn-default btn-xs add-cart"><span class="fa fa-cart-arrow-down"></span>加入购物车</a>\n' +
                            '</span>\n' +
                            '</div>\n' +
                            '</div>';
                        td = td.replace(/#{itemType}/g,list[j].itemType);
                        td = td.replace(/#{title}/g,list[j].title);
                        td = td.replace(/#{price}/g,list[j].price);
                        td = td.replace(/#{priority}/g,list[j].priority);
                        td = td.replace(/#{image}/g,list[j].image);
                        td = td.replace(/#{id}/g,list[j].id);
                        tr += td;
                    }
                    tr += "</div>";
                    $("#allList").append(tr);
                }
            }
            else {
                alert("获取化妆品列表失败！"+ json.message);
            }
        },
        error: function(xhr){
            alert("获取化妆品列表时产生未知的异常!" + xhr.message);
        }
    });
};