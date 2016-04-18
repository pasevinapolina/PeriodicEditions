$('.myReader').click(function () {
    $("#readerName").attr("value", $(this).children(".readerId").text());
});