/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function addlike(idStatus,idlogin){
    fetch("/MXHTT/api/add-like/"+idStatus+"/"+idlogin,{
        method:'POST',
        
   
    })
}

function finishsell(idAucion){
    fetch("/MXHTT/api/finish-sell/"+idAucion+"/",{
        method:'POST',
        
        
    })
}
