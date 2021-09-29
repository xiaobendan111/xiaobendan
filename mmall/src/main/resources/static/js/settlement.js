$(function(){
    //计算总价
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseFloat($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

//商品数量++
// function addQuantity(obj){
//     var index = $(".car_btn_2").index(obj);
//     var stock = parseInt($(".productStock").eq(index).val());
//     var price = parseInt($(".productPrice").eq(index).val());
//     var inputObj = $(".car_ipt").eq(index);
//     var quantity = inputObj.val();
//     var id = $(".id").eq(index).val();
//     ++quantity;
//     if(quantity > stock){
//         quantity = stock;
//         alert("库存不足！");
//     }
//     var cost = quantity*price;
//     $.ajax({
//         url:"/product/updateCart/"+id+"/"+quantity+"/"+cost,
//         type:"POST",
//         dataType:"text",
//         success:function (data) {
//             if(data == "success"){
//                 //更新toSettlement的数据
//                 $(".qprice").eq(index).html("￥"+cost);
//                 inputObj.val(quantity);
//                 if(quantity < stock){
//                     var totalCost = parseInt($("#totalprice").html().substring(1));
//                     totalCost += price;
//                     $("#totalprice").html("￥"+totalCost);
//                 }
//                 //更新searchBar的数据
//                 $(".quantity").eq(index).text(quantity);
//                 $(".cost").eq(index).text(cost);
//
//                 var array = $(".cost");
//                 var totalCost = 0;
//                 for(var i = 0;i < array.length;i++){
//                     var val = parseInt($(".cost").eq(i).html());
//                     totalCost += val;
//                 }
//                 $("#totalCost").html("￥"+totalCost);
//             }
//         }
//     });
// }
function addQuantity(obj){
    var index = $(".car_btn_2").index(obj);
    var quantity = parseInt($(".car_ipt").eq(index).val());
    var quantity1 = parseInt($(".productQuantity").eq(index).val());
    var stock = parseInt($(".productStock").eq(index).val());
    var stock1 = quantity1+stock;
    if (quantity==stock1){
        alert("库存不足！");
        return false;
    }
    quantity++
    $(".car_ipt").eq(index).val(quantity)
    var price = parseFloat($(".productPrice").eq(index).val())
    var cost = parseFloat(price*quantity)
    $(".qprice").eq(index).text('￥'+cost)
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseFloat($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    var id = $(".id").eq(index).val()
    //ajax
    $.ajax({
        url:"/cart/update/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        // success:function (data) {
        //     console.log(data)
        // }
    })

}
function subQuantity(obj){
    var index = $(".car_btn_1").index(obj);
    var quantity = parseInt($(".car_ipt").eq(index).val());
    quantity--
    if (quantity<=0){
        alert("至少选择一件商品");
        return false;
    }
    $(".car_ipt").eq(index).val(quantity)
    var price = parseFloat($(".productPrice").eq(index).val())
    var cost = price*quantity
    $(".qprice").eq(index).text('￥'+cost)
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseFloat($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    var id = $(".id").eq(index).val()

    $.ajax({
        url:"/cart/update/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        // success:function (data) {
        //     console.log(data)
        // }
    })
}
//商品数量--
// function subQuantity(obj){
//     var index = $(".car_btn_1").index(obj);
//     var price = parseInt($(".productPrice").eq(index).val());
//     var inputObj = $(".car_ipt").eq(index);
//     var quantity = inputObj.val();
//     var id = $(".id").eq(index).val();
//     --quantity;
//     if(quantity == 0){
//         quantity = 1;
//     }
//     var cost = quantity*price;
//     $.ajax({
//         url:"/product/updateCart/"+id+"/"+quantity+"/"+cost,
//         type:"POST",
//         dataType:"text",
//         success:function(data){
//             if(data == "success"){
//                 $(".qprice").eq(index).html("￥"+cost);
//                 inputObj.val(quantity);
//                 if(quantity!=1){
//                     var totalCost = parseInt($("#totalprice").html().substring(1));
//                     totalCost -= price;
//                     $("#totalprice").html("￥"+totalCost);
//                 }
//                 $(".quantity").eq(index).text(quantity);
//                 $(".cost").eq(index).text(cost);
//
//                 var array = $(".cost");
//                 var totalCost = 0;
//                 for(var i = 0;i < array.length;i++){
//                     var val = parseInt($(".cost").eq(i).html());
//                     totalCost += val;
//                 }
//                 $("#totalCost").html("￥"+totalCost);
//             }
//         }
//     });
// }

//移出购物车
function removeCart(id){
    // var id = $(".id").val();
    if(confirm("是否确定要删除？")){
        window.location.href = "/cart/removeCart/"+id;
    }
}


//有东西才能点
function settlement2() {
    var totalCost = $("#totalCost").text();
    if (totalCost == "￥0"){
        alert("啥也没有你买个屁啊！")
    }else {
        window.location.href="/cart/settlement2"
    }
}