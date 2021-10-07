/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function addlike(idStatus,idUser){
    fetch("/MXHTT/api/add-like/"+idStatus,{
        method:'POST',
        
    })
}