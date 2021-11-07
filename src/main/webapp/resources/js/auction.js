/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function auctionstt(id, auctionLabels = [], auctionInfo = []) {
    const data = {
        labels: [
            'Chưa thanh toán',
            'Chờ xác nhận',
            'Yêu cầu thanh toán lại',
            'Hoàn thành'
        ],
        datasets: [{
                label: 'Thống kê đấu giá theo trạng thái',
                data: auctionInfo,
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)',
                    'rgb(201, 203, 207)'

                ],
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'doughnut',
        data: data,
    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}
function StatusChart(id, statusLabels = [], statusInfo = []) {
    const data = {
        labels: statusLabels,
        datasets: [{
                label: 'Thống kê status theo hashtag',
                data: statusInfo,
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)',
                    'rgb(201, 203, 207)',
                    'rgb(52, 74, 48)'

                ],
                hoverOffset: 5
            }]
    };

    const config = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        },
    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)


}
function StatusTime(id, statustimeLabels = [], statustimeInfo = []) {
    const data = {
        labels: statustimeLabels,
        datasets: [{
                label: statustimeLabels,
                data: statustimeInfo,
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)',
                    'rgb(201, 203, 207)',
                    'rgb(52, 74, 48)'

                ],
                hoverOffset: 5
            }]
    };

    const config = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        },

    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)


}
function AuctionTime(id, auctiontimeLabels = [], auctiontimeInfo = []) {
    const data = {
        labels: auctiontimeLabels,
        datasets: [{
                label: auctiontimeLabels ,
                data: auctiontimeInfo,
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)',
                    'rgb(201, 203, 207)',
                    'rgb(52, 74, 48)'

                ],
                hoverOffset: 5
            }]
    };

    const config = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        },

    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)


}


