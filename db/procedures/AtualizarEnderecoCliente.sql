create or replace PROCEDURE AtualizarEnderecoCliente(
    p_cd_endereco IN t_fp_endereco.cd_endereco%type,
    p_nr_cep IN t_fp_endereco.nr_cep%type,
    p_nm_logradouro IN t_fp_endereco.nm_logradouro%type,
    p_nr_logradouro IN t_fp_endereco.nr_logradouro%type,
    p_ds_complemento IN t_fp_endereco.ds_complemento%type,
    p_nm_municipio IN t_fp_endereco.nm_municipio%type,
    p_cd_uf IN t_fp_uf.cd_uf%type
)
IS BEGIN
    UPDATE t_fp_endereco
        SET nr_cep = p_nr_cep,
            nm_logradouro = p_nm_logradouro,
            nr_logradouro = p_nr_logradouro,
            ds_complemento = p_ds_complemento,
            nm_municipio = p_nm_municipio,
            t_fp_uf_cd_uf = p_cd_uf
        WHERE cd_endereco = p_cd_endereco;

    COMMIT;
END;