$('document').ready(function() {
    $('.ed-del').click(function() {
        var id = $(this).parents('tr').children('td')[0].innerHTML;
        var name = $(this).parents('tr').children('td')[1].innerHTML;
        document.getElementById('editionName').innerHTML = name;
        $('#delEditionId').attr('value', id);
    });

    $('.ed-edit').click(function() {
        var id = $(this).parents('tr').children('td')[0].innerHTML;
        var name = $(this).parents('tr').children('td')[1].innerHTML;
        var author = $(this).parents('tr').children('td')[2].innerHTML;
        var freq = $(this).parents('tr').children('td')[3].innerHTML;

        $('#editEditionId').attr('value', id);
        $('#editEditionName').attr('value', name);
        $('#editAuthor').attr('value', author);
        $('#editFrequency').attr('value', freq);
    });
});