notification = false;
textNotification = $('#notification');
modalNotification = $('.modal-notification');

function showNotification(text, time) {

    if (time === null || time === undefined) {
        time = 2500;
    }

    textNotification.text(text);
    modalNotification.css({
        display: 'block'
    });
    modalNotification.removeClass('animated zoomOut');
    modalNotification.addClass('animated zoomIn');

    setTimeout(function () {
        hideNotification();
    }, time);
}

function hideNotification() {
    modalNotification.removeClass('animated zoomIn');
    modalNotification.addClass('animated zoomOut');
}