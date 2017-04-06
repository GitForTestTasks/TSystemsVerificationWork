$(document).ready(function () {
    setTimeout(function () {
        $('.loading').hide()
        $('.loading').remove()
    }, 500)

    setInterval(function () {

        $.ajax({
            url: '/renewcart',
            data: {},
            type: 'GET',
            success: function (msg) {
                $('.cart-size').html(msg)
            }
        })

    }, 1000)

})