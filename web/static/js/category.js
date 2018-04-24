$('#reg').click(function() {
    if ($('#category').val() == "") {
        $('#category').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font color='red'><b>×名称不能为空</b></font>");
        return false;
    }