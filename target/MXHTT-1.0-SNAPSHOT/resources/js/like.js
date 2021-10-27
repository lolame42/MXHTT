function addlike(idStatus, idlogin) {
    fetch("/MXHTT/api/add-like/" + idStatus + "/" + idlogin, {
        method: 'POST',
   
    })
    var test = ".hihi"+idStatus.toString()
    var test1 = document.querySelector(test)
    test1.setAttribute('style','visibility:hidden;')
}

function finishsell(idAucion) {
    fetch("/MXHTT/api/finish-sell/" + idAucion + "/", {
        method: 'POST',

    })
}
