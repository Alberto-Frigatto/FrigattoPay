create or replace PROCEDURE InserirEnderecoCliente(
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_nr_cep IN t_fp_endereco.nr_cep%type,
    p_nm_logradouro IN t_fp_endereco.nm_logradouro%type,
    p_nr_logradouro IN t_fp_endereco.nr_logradouro%type,
    p_ds_complemento IN t_fp_endereco.ds_complemento%type,
    p_cd_tipo_logradouro IN t_fp_endereco.t_fp_tipo_lograd_cd_tipo%type,
    p_cd_bairro IN t_fp_endereco.t_fp_bairro_cd_bairro%type
)
IS
    v_cd_endereco NUMBER;
BEGIN
    INSERT INTO t_fp_endereco
        (
            cd_endereco,
            nr_cep,
            nm_logradouro,
            nr_logradouro,
            ds_complemento,
            t_fp_tipo_lograd_cd_tipo,
            t_fp_bairro_cd_bairro,
            t_fp_cliente_cd_cliente
        )
        VALUES (
            seq_endereco.nextval,
            p_nr_cep,
            p_nm_logradouro,
            p_nr_logradouro,
            p_ds_complemento,
            p_cd_tipo_logradouro,
            p_cd_bairro,
            p_cd_cliente
        );

    COMMIT;
END;