function searchingLocation(locationNum){
    $.ajax({
        url: "list/"+locationNum,
        type: "GET",
        data: { "locationNum": locationNum },
        dataType: "JSON",
        success:function(map){
            
            // const landMarkList = document.getElementById("landMarkList");
            // 이 요소는 좀 생각해봐야할지도 
            const ol = document.getElementById("landMakrList");
            //이거 맞던가

            for(let landMark of map.landMarkList){
                // console.log(map.landMarkList);
                // console.log(landMark);
                // const landRow = document.createElement("li");
                // landRow.classList.add("land-row");

                // ol.append(landRow);

                // const figure = document.createElement("figure")
                // const landMarkImage = document.createElement("img");
                // landMarkImage.setAttribute("src",landMark.thumbnail);
                // figure.append(landMarkImage);
                // console.log(landMark.thumbnail);
                
                // const figcaption = document.createElement("figcaption");
                // figcaption.classList.add("landMark");
                // // 아이디 어케주더라

                // const landMarkTitle = document.createElement("p");
                // landMarkTitle.innerText = landMark.landMarkTitle;
                // landMarkTitle.classList.add("land-Title");
                // console.log(landMark.landMarkTitle);

                // const landMarkContent = document.createElement("p");
                // landMarkContent.innerText = landMark.landMarkContent;
                // landMarkContent.classList.add("land-content");
                // console.log(landMark.landMarkContent);
                
                // figcaption.append(landMarkTitle,landMarkContent);

                // landRow.append(figure,figcaption);

                // 빠진거 있는지 봐봐야겟네요 

                const li = document.createElement('li');
                const figure = document.createElement('figure');
                const img = document.createElement('img');
                const figcaption = document.createElement('figcaption');
                const p1 = document.createElement('p');
                const p2 = document.createElement('p');
                li.append(figure);
                figure.append(img);
                img.setAttribute("src",contextPath+landMark.thumbnail);
                figure.append(figcaption);
                p1.innerText=landMark.landMarkName;
                p2.innerText=landMark.landMarkContent;
                figcaption.append(p1);
                figcaption.append(p2);
                ol.append(li);

                //일단 여기까지 합시다
            }
        }
    })
}