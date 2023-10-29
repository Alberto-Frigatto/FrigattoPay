create or replace PROCEDURE InserirEnderecoCliente(
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_nr_cep IN t_fp_endereco.nr_cep%type,
    p_nm_logradouro IN t_fp_endereco.nm_logradouro%type,
    p_nr_logradouro IN t_fp_endereco.nr_logradouro%type,
    p_ds_complemento IN t_fp_endereco.ds_complemento%type,
    P_nm_municipio IN t_fp_endereco.nm_municipio%type,
    p_cd_uf IN t_fp_uf.cd_uf%type
)
IS BEGIN
    INSERT INTO t_fp_endereco
        (
            cd_endereco,
            nr_cep,
            nm_logradouro,
            nr_logradouro,
            ds_complemento,
            nm_municipio,
            t_fp_uf_cd_uf,
            t_fp_cliente_cd_cliente
        )
        VALUES (
            seq_endereco.nextval,
            p_nr_cep,
            p_nm_logradouro,
            p_nr_logradouro,
            p_ds_complemento,
            P_nm_municipio,
            p_cd_uf,
            p_cd_cliente
        );

    COMMIT;
END;