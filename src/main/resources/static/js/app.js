$(function () {
    var arrowsUp = $('.float-right').children('#arrowup');
    console.log(arrowsUp.toArray().length)
    arrowsUp.on('click', function (e) {
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

        console.log(postId)

    })
})

