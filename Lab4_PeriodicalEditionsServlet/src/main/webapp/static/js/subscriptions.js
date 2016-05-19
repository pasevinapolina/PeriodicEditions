$('document').ready(function() {
    $('.myReader').click(function () {
        $("#readerName").attr("value", $(this).children(".readerId").text());
    });

    $('.myEdition').click(function () {
        $("#selEditionId").attr('value', $(this).children(".editionId").text());
    });
});