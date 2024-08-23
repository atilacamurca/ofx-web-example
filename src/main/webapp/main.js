
$(function () {
    $('#btn-enviar').click(function (e) {
        e.preventDefault();
        const formData = new FormData($('#main-form')[0]);
        const promise = $.ajax({
            url: `/ofx-web-example-1.0-SNAPSHOT/resources/importar/ofx`,
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false
        });
        promise.done(json => {
            console.log('Arquivo importado com sucesso.');
            console.log(json);
        });
        promise.fail((jqXHR, textStatus, errorThrown) => {
            if (jqXHR.status === 415) {
                console.log('Nenhum arquivo enviado.');
            } else {
                console.log(jqXHR, textStatus, errorThrown);
            }
        });
    });
});
