function addlike(idStatus, idlogin) {
    fetch("/MXHTT/api/add-like/" + idStatus + "/" + idlogin, {
        method: 'POST',
   
    })
    var test = ".hihi"+idStatus.toString()
    var test1 = document.querySelector(test)
    test1.setAttribute('style','visibility:hidden;')
}

window.onload = function () {
    let dates = document.querySelectorAll(".my-date>p")
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
})


         

