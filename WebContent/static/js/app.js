$(() => {
    $('.cpf-input').mask('000.000.000-00')
    $('.cnpj-input').mask('00.000.000/0000-00')
    $('.valor-input').mask('000000000000.00', {reverse: true});
})
