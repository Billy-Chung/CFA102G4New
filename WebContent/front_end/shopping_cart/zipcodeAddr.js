

function injectData() {
    //從zipCode.js 拿到城市key物件的zipCodeData名稱，命名為cities 
    const cities = Object.keys(zipCodeData);
    //get元件id為city，命名為cityElement
    const cityElement = document.getElementById("city");

    //當key是cities時跑for迴圈
    for (const key in cities) {
        //創建選擇器元素，命名為opt
        const opt = document.createElement("option");
        //用cities的key取得對應的opt的值
        opt.value = cities[key];
        //把cities的key值，顯示在網頁上
        opt.innerHTML = cities[key];
        //顯示cityElement的子項目
        cityElement.appendChild(opt);
    }
    //在console顯示city的值
    console.log(city.value)

    //cityElement的值，就是當前的值
    const currentValue = cityElement.value;
    //從zipCode.js key為zipCodeData的值，命名為distinct
    const distinct = zipCodeData[currentValue];

    //從zipCode.js 拿到區域key物件的zipCodeData名稱，命名為distinctKeys 
    //例如選擇新北市，出現對應的值從萬里區、金山區~石門區，命名為distinctKeys 
    const distinctKeys = Object.keys(distinct)
    //取得dom物件，所選的ID值，命名為distinctElement
    //例如選擇地區為土城
    const distinctElement = document.getElementById("distinct");


    //當key是distinctKeys時跑for迴圈
    for (const key in distinctKeys) {
        //創建選擇器元素，命名為opt2
        const opt2 = document.createElement("option");
        //用distinctKeys的key取得對應的opt2的值
        opt2.value = distinctKeys[key];
        //把distinctKeys的key值，顯示在網頁上
        opt2.innerHTML = distinctKeys[key];
        //顯示distinctKeys的子項目
        distinctElement.appendChild(opt2);
    }
    //在console顯示distinctElement的值
    console.log(distinctElement.value);
}

// //當城市改變時，對應的區域變化
function whenCityChange() {
    //情境:只出現該城市對應的區域
console.log(123);
    //get元件id為city，命名為cityElement
    const cityElement = document.getElementById("city");
    //cityElement的值，就是當前的值
    const currentValue = cityElement.value;
    //在console顯示city的值
    console.log(currentValue);
    //從zipCode.js 被選擇的城市key為zipCodeData的值，命名為被選擇城市的區域distinct
    const distinct = zipCodeData[currentValue];
    console.log(distinct);

    //從zipCode.js 拿到區域key物件的zipCodeData名稱，命名為distinctKeys 
    //例如選擇新北市，出現對應的值從萬里區、金山區~石門區，命名為distinctKeys 
    const distinctKeys = Object.keys(distinct)
    // 用ID值(distinct)取出畫面元素中id=distinct的dom物件
    let distinctElement = document.getElementById("distinct");
    distinctElement.innerHTML ="";


    //對distinctKeys跑for迴圈
    //distinctKeys 型別是陣列
    //key為城市對應的區域(distinct)例如選擇新北市，出現對應的值從萬里區、金山區~石門區，命名為distinctKeys 
    for (const key in distinctKeys) {
        //key 是 index

        //用ID值(option)建立元素類型為 option 的dom物件，命名為opt2
        const opt2 = document.createElement("option");
        //key為萬里區=opt2的值
        //例如index=0，則取出萬里區
        opt2.value = distinctKeys[key];
        //把萬里區顯示在網頁上
        opt2.innerHTML = distinctKeys[key];
        //select原本是空的，用appendChild一個一個加入
        //將一个節點(option類型的dom物件 opt2)附加到指定父節點(id=distinct的select物件)的子节点列表的(例如萬里區+金山區+...)末尾处
        distinctElement.appendChild(opt2);
    }












}














//  }