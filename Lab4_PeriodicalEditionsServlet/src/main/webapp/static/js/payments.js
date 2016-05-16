$('document').ready(function() {
    $('.pay-del').click(function() {
        var id = $(this).parents('tr').attr('id');
        $('#delPayId').attr('value', id);
    });

    $('.pay-edit').click(function() {
        var id = $(this).parents('tr').attr('id');
        var sum = $(this).parents('tr').children('td')[4].childNodes[0].nodeValue;
        var date = $(this).parents('tr').children('td')[3].childNodes[0].nodeValue;

        var dateArr = date.split('.', 3);

        $('#editPayId').attr('value', id);
        $('#editPaySum').attr('value', sum);
        $('#editPayDate').attr('value', dateArr[2] + '-' + dateArr[1] + '-' + dateArr[0]);
    });
});

function countPaySum() {
    var days = document.getElementById('duration').value;
    document.getElementById('paySum').setAttribute('placeholder', (days * 37.2).toFixed(2));
}