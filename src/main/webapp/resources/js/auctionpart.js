
window.onload = function () {
    let dates = document.querySelectorAll(".tgcmt > p")
    for (let i = 0; i < dates.length; i++)
        {
            let d = dates[i]
            d.innerText = moment(d.innerText).fromNow()
        }
    }
