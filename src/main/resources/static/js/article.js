// 삭제 기능

const deleteButton=document.getElementById("delete-btn");

if(deleteButton){
    deleteButton.addEventListener('click',event=>{
       let id=document.getElementById('article-id').value;
       //let id=document.querySelector('#article-id').value;

       fetch(`/api/articles/${id}`,{
           method:'DELETE'
       })
           .then(()=>{
               alert('삭제가 완료되었습니다.');
               location.replace('/articles');
           });
    });
}

//수정기능 -> 입력한 자료 가져감,json
//const modifyButton=document.getElementById("modify-btn")
const modifyButton=document.querySelector("#modify-btn");

if(modifyButton){
    modifyButton.addEventListener('click',event=>{
       let params=new URLSearchParams(location.search);
       let id=params.get('id');

       fetch(`/api/articles/${id}`,{
           method:'PUT',
           headers:{
               "Content-Type":"application/json",
           },
           body:JSON.stringify({
               title:document.getElementById('title').value,
               content:document.getElementById('content').value
           }) //JSOn 형식으로 변경
       })
           .then(()=>{
               alert('수정이 완료되었습니다.');
               location.replace(`/articles/${id}`);
           });
    });
}

//등록 처리 (생성 처리)
const createButton=document.getElementById('create-btn');

if(createButton){
    createButton.addEventListener('click',event=>{
        fetch('/api/articles', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title:document.getElementById('title').value,
                content:document.getElementById('content').value
            })
        })
           .then(()=>{
               alert('등록 완료되었습니다.');
               location.replace('/articles');
           });
        });
}