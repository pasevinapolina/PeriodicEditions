$('document').ready(function() {
    $('.pay-del').click(function() {
        var id = $(this).parents('tr').attr('id');
        $('#delPayId').attr('value', id);
    });
});

$('document').ready(function() {
    $('.pay-edit').click(function() {
        var id = $(this).parents('tr').attr('id');

        $('#editPayId').attr('value', id);
        $('#editPaySum').attr('value', $(this).parents('tr').children('#tdSum').children().value);
        $('#editDuration').attr('value', $(this).parents('tr').children('#tdDate').children().value);
        $('#editPayDate').attr('value', $(this).parents('tr').children('#tdDate').children().value);
    });
});

function countPaySum() {
    var days = document.getElementById('duration').value;
    document.getElementById('paySum').setAttribute('placeholder', (days * 37.2).toFixed(2));
}