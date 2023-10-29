CREATE TABLE t_fp_banco (    
    nr_cnpj           CHAR(14) NOT NULL,
    nm_banco_parceiro VARCHAR2(50) NOT NULL,
    ds_email          VARCHAR2(320) NOT NULL,
    vl_taxa_servicos  CLOB NOT NULL
);

ALTER TABLE t_fp_banco ADD CONSTRAINT t_fp_banco_pk PRIMARY KEY ( nr_cnpj );

CREATE TABLE t_fp_bandeira (
    cd_bandeira     NUMBER(9) NOT NULL,
    nm_bandeira     VARCHAR2(20) NOT NULL,
    vl_caminho_foto VARCHAR2(255) NOT NULL
);

ALTER TABLE t_fp_bandeira ADD CONSTRAINT t_fp_bandeira_pk PRIMARY KEY ( cd_bandeira );

CREATE TABLE t_fp_cartao (
    nr_cartao                 NUMBER(16) NOT NULL,
    dt_validade               DATE NOT NULL,
    nr_cvv                    VARCHAR2(3) NOT NULL,
    vl_cartao_desbloqueado    NUMBER NOT NULL,
    t_fp_tipo_cartao_cd_tipo  NUMBER(9) NOT NULL,
    t_fp_bandeira_cd_bandeira NUMBER(9) NOT NULL,
    t_fp_conta_cd_conta       NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_cartao ADD CONSTRAINT t_fp_cartao_pk PRIMARY KEY ( nr_cartao );

CREATE TABLE t_fp_cliente (
    cd_cliente NUMBER(9) NOT NULL,
    nm_cliente VARCHAR2(50 CHAR) NOT NULL,
    ds_email   VARCHAR2(320 CHAR) NOT NULL,
    ds_senha   VARCHAR2(255 CHAR) NOT NULL
);

ALTER TABLE t_fp_cliente ADD CONSTRAINT t_fp_cliente_pk PRIMARY KEY ( cd_cliente );

CREATE TABLE t_fp_cliente_pf (
    t_fp_cliente_cd_cliente NUMBER(9) NOT NULL,
    nr_cpf                  CHAR(11) NOT NULL,
    nr_rg                   CHAR(11) NOT NULL,
    dt_nascimento           DATE NOT NULL
);

ALTER TABLE t_fp_cliente_pf ADD CONSTRAINT t_fp_cliente_pf_pk PRIMARY KEY ( t_fp_cliente_cd_cliente );

CREATE TABLE t_fp_cliente_pj (
    t_fp_cliente_cd_cliente NUMBER(9) NOT NULL,
    nr_cnpj                 CHAR(14) NOT NULL,
    dt_abertura             DATE NOT NULL,
    nr_inscricao_estadual   VARCHAR2(20) NOT NULL,
    ds_setor                VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE t_fp_cliente_pj ADD CONSTRAINT t_fp_cliente_pj_pk PRIMARY KEY ( t_fp_cliente_cd_cliente );

CREATE TABLE t_fp_conta (
    cd_conta                NUMBER(9) NOT NULL,
    nr_conta                VARCHAR2(9) NOT NULL,
    vl_saldo                NUMBER(14, 2) NOT NULL,
    t_fp_tipo_conta_cd_tipo NUMBER(9) NOT NULL,
    t_fp_cliente_cd_cliente NUMBER(9) NOT NULL,
    t_fp_banco_nr_cnpj      CHAR(14),
    nr_agencia              VARCHAR2(10)
);

ALTER TABLE t_fp_conta ADD CONSTRAINT t_fp_conta_pk PRIMARY KEY ( cd_conta );

ALTER TABLE t_fp_conta_conj ADD CONSTRAINT t_fp_conta_conj_pk PRIMARY KEY ( cd_conta_conj );

CREATE TABLE t_fp_conta_invs (
    cd_conta_invs       NUMBER(9) NOT NULL,
    dt_invs             DATE NOT NULL,
    vl_invs             NUMBER(10, 2) NOT NULL,
    t_fp_invs_cd_invs   NUMBER(9) NOT NULL,
    t_fp_conta_cd_conta NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_conta_invs ADD CONSTRAINT t_fp_conta_invs_pk PRIMARY KEY ( cd_conta_invs );

CREATE TABLE t_fp_ddd (
    nr_ddd        NUMBER(2) NOT NULL,
    t_fp_uf_cd_uf NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_ddd ADD CONSTRAINT t_fp_ddd_pk PRIMARY KEY ( nr_ddd );

CREATE TABLE t_fp_despesa (
    cd_despesa                NUMBER(9) NOT NULL,
    nm_despesa                VARCHAR2(30 CHAR) NOT NULL,
    vl_despesa                NUMBER(8, 2) NOT NULL,
    ds_despesa                VARCHAR2(300 CHAR) NOT NULL,
    dt_vencimento             DATE,
    t_fp_cliente_cd_cliente   NUMBER(9) NOT NULL,
    t_fp_tipo_despesa_cd_tipo NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_despesa ADD CONSTRAINT t_fp_despesa_pk PRIMARY KEY ( cd_despesa );

CREATE TABLE t_fp_empr (
    cd_empr               NUMBER(9) NOT NULL,
    vl_empr               NUMBER(8, 2) NOT NULL,
    vl_juros              NUMBER(4, 3) NOT NULL,
    dt_prazo              DATE NOT NULL,
    dt_solicitacao        DATE NOT NULL,
    dt_aprovacao          DATE,
    vl_parcela            NUMBER(8, 2) NOT NULL,
    dt_vencimento_parcela DATE NOT NULL,
    t_fp_conta_cd_conta   NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_empr ADD CONSTRAINT t_fp_empr_pk PRIMARY KEY ( cd_empr );

CREATE TABLE t_fp_endereco (
    cd_endereco              NUMBER(9) NOT NULL,
    nr_cep                   CHAR(8) NOT NULL,
    nm_logradouro            VARCHAR2(40) NOT NULL,
    nr_logradouro            NUMBER(4) NOT NULL,
    ds_complemento           VARCHAR2(30),
    nm_municipio            VARCHAR2(70) NOT NULL,
    t_fp_uf_cd_uf            NUMBER(9),
    t_fp_cliente_cd_cliente  NUMBER(9)
);

ALTER TABLE t_fp_endereco ADD CONSTRAINT t_fp_endereco_pk PRIMARY KEY ( cd_endereco );

CREATE TABLE t_fp_invs (
    cd_invs     NUMBER(9) NOT NULL,
    nm_invs     VARCHAR2(100) NOT NULL,
    ds_invs     VARCHAR2(500) NOT NULL,
    vl_minimo   NUMBER(8, 2) NOT NULL,
    vl_risco    NUMBER(2) NOT NULL,
    vl_liquidez NUMBER(2) NOT NULL,
    vl_taxa     NUMBER(4, 3) NOT NULL
);

ALTER TABLE t_fp_invs ADD CONSTRAINT t_fp_invs_pk PRIMARY KEY ( cd_invs );

CREATE TABLE t_fp_pagmt (
    cd_pagmt                VARCHAR2(44) NOT NULL,
    nm_pagmt                VARCHAR2(30 CHAR) NOT NULL,
    dt_pagmt                DATE NOT NULL,
    vl_pagmt                NUMBER(8, 2) NOT NULL,
    t_fp_tipo_pagmt_cd_tipo NUMBER(9) NOT NULL,
    t_fp_conta_cd_conta     NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_pagmt ADD CONSTRAINT t_fp_pagmt_pk PRIMARY KEY ( cd_pagmt );

CREATE TABLE t_fp_parcela_empr (
    cd_parcela          NUMBER(9) NOT NULL,
    vl_pago             NUMBER(8, 2) NOT NULL,
    dt_pagamento        DATE NOT NULL,
    vl_juros            NUMBER(5, 3),
    t_fp_empr_cd_empr   NUMBER(9) NOT NULL,
    t_fp_conta_cd_conta NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_parcela_empr ADD CONSTRAINT t_fp_parcela_empr_pk PRIMARY KEY ( cd_parcela );

CREATE TABLE t_fp_receita (
    cd_receita                NUMBER(9) NOT NULL,
    vl_receita                NUMBER(8, 2) NOT NULL,
    dt_receita                DATE NOT NULL,
    t_fp_cliente_cd_cliente   NUMBER(9) NOT NULL,
    t_fp_tipo_receita_cd_tipo NUMBER(9) NOT NULL
);

ALTER TABLE t_fp_receita ADD CONSTRAINT t_fp_receita_pk PRIMARY KEY ( cd_receita );

CREATE TABLE t_fp_telefone (
    cd_telefone              NUMBER(9) NOT NULL,
    nr_telefone              VARCHAR2(9) NOT NULL,
    nr_ramal                 NUMBER(4) NULL,
    t_fp_ddd_nr_ddd          NUMBER(2) NOT NULL,
    t_fp_cliente_cd_cliente  NUMBER(9),
);

ALTER TABLE t_fp_telefone ADD CONSTRAINT t_fp_telefone_pk PRIMARY KEY ( cd_telefone );

CREATE TABLE t_fp_tipo_cartao (
    cd_tipo NUMBER(9) NOT NULL,
    nm_tipo VARCHAR2(20) NOT NULL
);

ALTER TABLE t_fp_tipo_cartao ADD CONSTRAINT t_fp_tipo_cartao_pk PRIMARY KEY ( cd_tipo );

CREATE TABLE t_fp_tipo_conta (
    cd_tipo NUMBER(9) NOT NULL,
    nm_tipo VARCHAR2(20) NOT NULL
);

ALTER TABLE t_fp_tipo_conta ADD CONSTRAINT t_fp_tipo_conta_pk PRIMARY KEY ( cd_tipo );

CREATE TABLE t_fp_tipo_despesa (
    cd_tipo NUMBER(9) NOT NULL,
    nm_tipo VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE t_fp_tipo_despesa ADD CONSTRAINT t_fp_tipo_despesa_pk PRIMARY KEY ( cd_tipo );

CREATE TABLE t_fp_tipo_pagmt (
    cd_tipo NUMBER(9) NOT NULL,
    nm_tipo VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE t_fp_tipo_pagmt ADD CONSTRAINT t_fp_tipo_pagmt_pk PRIMARY KEY ( cd_tipo );

CREATE TABLE t_fp_tipo_receita (
    cd_tipo NUMBER(9) NOT NULL,
    nm_tipo VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE t_fp_tipo_receita ADD CONSTRAINT t_fp_tipo_receita_pk PRIMARY KEY ( cd_tipo );

CREATE TABLE t_fp_tipo_transc (
    cd_tipo NUMBER(9) NOT NULL,
    nm_tipo VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE t_fp_tipo_transc ADD CONSTRAINT t_fp_tipo_transc_pk PRIMARY KEY ( cd_tipo );

CREATE TABLE t_fp_transc (
    cd_transc                NUMBER(9) NOT NULL,
    vl_transc                NUMBER(9, 2) NOT NULL,
    dt_transc                DATE NOT NULL,
    t_fp_tipo_transc_cd_tipo NUMBER(9) NOT NULL,
    t_fp_conta_cd_conta2     NUMBER(9) NOT NULL,
    t_fp_conta_cd_conta      NUMBER(9)
);

CREATE UNIQUE INDEX t_fp_transc__idx ON
    t_fp_transc (
        t_fp_conta_cd_conta
    ASC );

ALTER TABLE t_fp_transc ADD CONSTRAINT t_fp_transc_pk PRIMARY KEY ( cd_transc );

CREATE TABLE t_fp_uf (
    cd_uf NUMBER(9) NOT NULL,
    nm_uf VARCHAR2(20) NOT NULL
);

ALTER TABLE t_fp_uf ADD CONSTRAINT t_fp_uf_pk PRIMARY KEY ( cd_uf );

ALTER TABLE t_fp_cartao
    ADD CONSTRAINT cartao_bandeira_fk FOREIGN KEY ( t_fp_bandeira_cd_bandeira )
        REFERENCES t_fp_bandeira ( cd_bandeira );

ALTER TABLE t_fp_cartao
    ADD CONSTRAINT cartao_conta_fk FOREIGN KEY ( t_fp_conta_cd_conta )
        REFERENCES t_fp_conta ( cd_conta );

ALTER TABLE t_fp_cartao
    ADD CONSTRAINT cartao_tipo_cartao_fk FOREIGN KEY ( t_fp_tipo_cartao_cd_tipo )
        REFERENCES t_fp_tipo_cartao ( cd_tipo );

ALTER TABLE t_fp_cliente_pf
    ADD CONSTRAINT cliente_pf_cliente_fk FOREIGN KEY ( t_fp_cliente_cd_cliente )
        REFERENCES t_fp_cliente ( cd_cliente ) ON DELETE CASCADE;

ALTER TABLE t_fp_cliente_pj
    ADD CONSTRAINT cliente_pj_cliente_fk FOREIGN KEY ( t_fp_cliente_cd_cliente )
        REFERENCES t_fp_cliente ( cd_cliente ) ON DELETE CASCADE;

ALTER TABLE t_fp_conta_invs
    ADD CONSTRAINT conta_invs_conta_fk FOREIGN KEY ( t_fp_conta_cd_conta )
        REFERENCES t_fp_conta ( cd_conta );

ALTER TABLE t_fp_conta_invs
    ADD CONSTRAINT conta_invs_invs_fk FOREIGN KEY ( t_fp_invs_cd_invs )
        REFERENCES t_fp_invs ( cd_invs );

ALTER TABLE t_fp_conta
    ADD CONSTRAINT conta_banco_fk FOREIGN KEY ( t_fp_banco_nr_cnpj )
        REFERENCES t_fp_banco ( nr_cnpj );

ALTER TABLE t_fp_conta
    ADD CONSTRAINT conta_cliente_fk FOREIGN KEY ( t_fp_cliente_cd_cliente )
        REFERENCES t_fp_cliente ( cd_cliente );

ALTER TABLE t_fp_conta
    ADD CONSTRAINT conta_tipo_conta_fk FOREIGN KEY ( t_fp_tipo_conta_cd_tipo )
        REFERENCES t_fp_tipo_conta ( cd_tipo );

ALTER TABLE t_fp_ddd
    ADD CONSTRAINT ddd_uf_fk FOREIGN KEY ( t_fp_uf_cd_uf )
        REFERENCES t_fp_uf ( cd_uf );

ALTER TABLE t_fp_despesa
    ADD CONSTRAINT despesa_cliente_fk FOREIGN KEY ( t_fp_cliente_cd_cliente )
        REFERENCES t_fp_cliente ( cd_cliente ) ON DELETE CASCADE;

ALTER TABLE t_fp_despesa
    ADD CONSTRAINT despesa_tipo_despesa_fk FOREIGN KEY ( t_fp_tipo_despesa_cd_tipo )
        REFERENCES t_fp_tipo_despesa ( cd_tipo );

ALTER TABLE t_fp_empr
    ADD CONSTRAINT empr_conta_fk FOREIGN KEY ( t_fp_conta_cd_conta )
        REFERENCES t_fp_conta ( cd_conta );

ALTER TABLE t_fp_endereco
    ADD CONSTRAINT endereco_uf_fk FOREIGN KEY ( t_fp_uf_cd_uf )
        REFERENCES t_fp_uf ( cd_uf );

ALTER TABLE t_fp_endereco
    ADD CONSTRAINT endereco_cliente_fk FOREIGN KEY ( t_fp_cliente_cd_cliente )
        REFERENCES t_fp_cliente ( cd_cliente ) ON DELETE CASCADE;

ALTER TABLE t_fp_pagmt
    ADD CONSTRAINT pagmt_conta_fk FOREIGN KEY ( t_fp_conta_cd_conta )
        REFERENCES t_fp_conta ( cd_conta );

ALTER TABLE t_fp_pagmt
    ADD CONSTRAINT pagmt_tipo_pagmt_fk FOREIGN KEY ( t_fp_tipo_pagmt_cd_tipo )
        REFERENCES t_fp_tipo_pagmt ( cd_tipo );

ALTER TABLE t_fp_parcela_empr
    ADD CONSTRAINT parcela_empr_conta_fk FOREIGN KEY ( t_fp_conta_cd_conta )
        REFERENCES t_fp_conta ( cd_conta );

ALTER TABLE t_fp_parcela_empr
    ADD CONSTRAINT parcela_empr_empr_fk FOREIGN KEY ( t_fp_empr_cd_empr )
        REFERENCES t_fp_empr ( cd_empr );

ALTER TABLE t_fp_receita
    ADD CONSTRAINT receita_cliente_fk FOREIGN KEY ( t_fp_cliente_cd_cliente )
        REFERENCES t_fp_cliente ( cd_cliente ) ON DELETE CASCADE;

ALTER TABLE t_fp_receita
    ADD CONSTRAINT receita_tipo_receita_fk FOREIGN KEY ( t_fp_tipo_receita_cd_tipo )
        REFERENCES t_fp_tipo_receita ( cd_tipo );

ALTER TABLE t_fp_telefone
    ADD CONSTRAINT telefone_ddd_fk FOREIGN KEY ( t_fp_ddd_nr_ddd)
        REFERENCES t_fp_ddd ( nr_ddd );

ALTER TABLE t_fp_telefone
    ADD CONSTRAINT telefone_cliente_fk FOREIGN KEY ( t_fp_cliente_cd_cliente )
        REFERENCES t_fp_cliente ( cd_cliente ) ON DELETE CASCADE;

ALTER TABLE t_fp_transc
    ADD CONSTRAINT transc_conta_fk FOREIGN KEY ( t_fp_conta_cd_conta )
        REFERENCES t_fp_conta ( cd_conta );

ALTER TABLE t_fp_transc
    ADD CONSTRAINT transc_conta_fkv2 FOREIGN KEY ( t_fp_conta_cd_conta2 )
        REFERENCES t_fp_conta ( cd_conta );

ALTER TABLE t_fp_transc
    ADD CONSTRAINT transc_tipo_transc_fk FOREIGN KEY ( t_fp_tipo_transc_cd_tipo )
        REFERENCES t_fp_tipo_transc ( cd_tipo );

COMMIT;