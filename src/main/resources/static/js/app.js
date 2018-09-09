$(function () {
    ///// VOTING ON POSTS
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

    ///// VOTING ON COMMENTS
    var arrowsUpComment = $('a.arrowupcomment');
    arrowsUpComment.one('click', function (e) {
        e.preventDefault();
        $(this).addClass("notlink");
        var pointsBadge = $(this).siblings('#commentpointsbadge');

        var arrowsDownComment = $(this).siblings('.arrowdowncomment');
        $(arrowsDownComment).remove();

        $(this).find('i').removeClass('greyarrow').addClass('greenarrow')
        pointsBadge.removeClass('badge-secondary').addClass('badge-success')

        var commmentId = $(this).attr('data-pid');
        var contextPath = $(this).attr('data-contextpath');

        $.ajax({
            url: contextPath + "/app/votedupcomment/" + commmentId,
            type: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        })
    })
    arrowsUpComment.on('click', function (e) {
        e.preventDefault();
    })

    var arrowsDownComment = $('a.arrowdowncomment');
    arrowsDownComment.one('click', function (e) {
        e.preventDefault();
        $(this).addClass("notlink");
        var pointsBadge = $(this).siblings('#commentpointsbadge');

        var arrowsUpComment = $(this).siblings('.arrowupcomment');
        $(arrowsUpComment).remove();

        $(this).find('i').removeClass('greyarrow').addClass('redarrow')
        pointsBadge.removeClass('badge-secondary').addClass('badge-danger')

        var commmentId = $(this).attr('data-pid');
        var contextPath = $(this).attr('data-contextpath');

        $.ajax({
            url: contextPath + "/app/voteddowncomment/" + commmentId,
            type: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        })
    })
    arrowsDownComment.on('click', function (e) {
        e.preventDefault();
    })

})

