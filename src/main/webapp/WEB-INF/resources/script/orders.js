$(document).ready(function () {
    $('.spinner').spinner()

    function onlyDigit() {
        var value = $(this).val()
        value = value.replace(/[^0-9]/g, '')

        var minval = $(this).attr('min')
        var maxval = $(this).attr('max')

        minval = parseInt(minval)
        maxval = parseInt(maxval)

        if (value == '' || value < minval) {
            value = minval
        }
        if (value > maxval) {
            value = maxval
        }

        $(this).val(value)
    }

    $('.spinner').on('change', onlyDigit)
    $('.spinner').on('keyup', onlyDigit)
    $('.spinner').on('keydown', function (e) {
        var key = e.keyCode
        var conditionOne = 48 <= key && key <= 57 || 93 <= key;
        var conditionTwo = key <= 105 || 37 <= key && key <= 40;
        var conditionThree = key == 8 || key == 46;
        if (conditionOne && conditionTwo || conditionThree) {
            return true
        }
        return false
    })

    $('.product-btn-add').on('click', function () {
        var product_id = $(this).attr('data')
        var spinner = $(this).parent().parent().find('.spinner')
        var count = spinner.val()
        var controller = $(this).attr('url')
        $.ajax({
            url: controller,
            data: {
                goodId: product_id,
                quantity: count
            },
            type: 'POST'
        })
    })
})