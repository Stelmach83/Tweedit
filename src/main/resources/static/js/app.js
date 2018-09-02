$(function () {
    var arrowsUp = $('.float-right').children('#arrowup');
    arrowsUp.one('click', function (e) {
        e.preventDefault();
        $(this).find('i').removeClass('greyarrow').addClass('greenarrow')

        var postId = $(this).attr('data-pid');

        $.ajax({
            url: "http://localhost:8080/app/votedup/" + postId,
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
        $(this).find('i').removeClass('greyarrow').addClass('redarrow')

        var postId = $(this).attr('data-pid');

        $.ajax({
            url: "http://localhost:8080/app/voteddown/" + postId,
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

