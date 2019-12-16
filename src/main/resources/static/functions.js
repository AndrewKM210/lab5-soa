$(document).ready(function () {
    registerSearch();
    registerTemplate();
});

function registerSearch() {
    $("#search").submit(function (ev) {
        event.preventDefault();
        // Add the maxTweets attribute
        $.get($(this).attr('action'), {q: $("#q").val(), maxTweets: $("#maxTweets").val()}, function (data) {
            $("#resultsBlock").html(Mustache.render(template, data));
        });
    });
}

function registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}
