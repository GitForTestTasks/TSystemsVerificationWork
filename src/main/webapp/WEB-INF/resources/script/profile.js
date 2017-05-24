$(document).ready(function () {
    $('#BirthDate').datepicker({dateFormat: 'dd.mm.yy'})

    var Password = $('#Password')
    var Comfirm = $('#Comfirm')

    Password.on('change', validatePassword)
    Comfirm.on('keyup', validatePassword)

    function validatePassword() {
        if (Password.val() != Comfirm.val()) {
            Comfirm.get(0).setCustomValidity('Passwords do not match')
        } else {
            Comfirm.get(0).setCustomValidity('')
        }
    }
})