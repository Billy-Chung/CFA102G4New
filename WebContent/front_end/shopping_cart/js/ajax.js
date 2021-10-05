
function addToCart(productNo){

    //建立 ajax 需要的 request物件
    const xhttp = new XMLHttpRequest();

    const data = {
        productNo: productNo,
        action: "addCartAjax"
    }

    //在request物件的onload方法，設定傳回後的反應
    xhttp.onload = function () {
        const response =this.responseText;
        
        if(response==="success"){
            alert("商品已加入購物車")
        }

    }
    // 設定 request物件的傳輸形式、目的地
    // xhttp.open("POST", server + "/AjaxServlet", true);
    xhttp.open("POST", `${server}/Shopping_cartServlet`, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    const params = dataTransformer(data);
    //設定傳輸的內容
    xhttp.send(params);
}

function dataTransformer(data) {
    const keys = Object.keys(data);
    var result = "";
    for (const key in keys) {
        const name = keys[key];
        result += `${name}=${data[name]}&`;
    }
    return result;
}