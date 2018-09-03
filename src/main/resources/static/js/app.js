$(function () {
    var arrowsUp = $('.float-right').children('#arrowup');
    arrowsUp.one('click', function (e) {
        e.preventDefault();
        $(this).addClass("notlink");
        var pointsBadge = $(this).siblings('#pointsbadge');

        var arrowsDown = $(this).siblings('#arrowdown');
        $(arrowsDown).remove();

        $(this).find('i').removeClass('greyarrow').addClass('greenarrow')
        pointsBadge.removeClass('badge-secondary').addClass('badge-success')

        var postId = $(this).attr('data-pid');
        var contextPath = $(this).attr('data-contextpath');

        $.ajax({
            url: contextPath + "/app/votedup/" + postId,
            type: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        })
    })

    arrowsUp.on('click', function (e) {
        e.preventDefault();
    })

    var arrowsDown = $('.float-right').children('#arrowdown');
    arrowsDown.one('click', function (e) {
        e.preventDefault();
        $(this).addClass("notlink");
        var pointsBadge = $(this).siblings('#pointsbadge');

        var arrowsUp = $(this).siblings('#arrowup');
        $(arrowsUp).remove();

        $(this).find('i').removeClass('greyarrow').addClass('redarrow')
        pointsBadge.removeClass('badge-secondary').addClass('badge-danger')

        var postId = $(this).attr('data-pid');
        var contextPath = $(this).attr('data-contextpath');

        $.ajax({
            url: contextPath + "/app/voteddown/" + postId,
            type: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        })
    })

    arrowsDown.on('click', function (e) {
        e.preventDefault();
    })

})

