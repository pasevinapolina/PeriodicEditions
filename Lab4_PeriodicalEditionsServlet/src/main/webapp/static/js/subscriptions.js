$('document').ready(function() {
    $('.myReader').click(function () {
        $("#readerName").attr("value", $(this).children(".readerId").text());
    });

    $('.myEdition').click(function () {
        var name = $(this).children('.myEditionName').text();
        var id = $(this).children(".editionId").text();
        $("#selEditionId").attr('value', id);
        $("#editionSelect").text(name);
    });
});