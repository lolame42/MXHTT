window.onload = function () {
    let dates = document.querySelectorAll(".my-date>i")
    for (let i = 0; i < dates.length; i++)
    {
        let d = dates[i]
        d.innerText = moment(d.innerText).fromNow()
    }
}
$(document).ready(function (){
    $(".setting > .collapses").hide()
    $(".setting").click(function (){
        $(".setting > .collapses").show()           
    })
    
    $(".reporta").hide()
    $(".report").click(function (){
        $(".reporta").show()
    })
})

$(document).ready(function (){
    $(".collapses > .delete").click(function (){
        confirm("Bạn có chắc chắn muốn xóa bài viết này?")
    })
})

