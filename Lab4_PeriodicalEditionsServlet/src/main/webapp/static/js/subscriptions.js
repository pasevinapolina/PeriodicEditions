$('.readerList').click(function () {
    var readerName = document.getElementById('readerList').value;
    $(document).getElementsById('readerName').value = readerName;
    $(document).getElementById('readerForm').submit();
});