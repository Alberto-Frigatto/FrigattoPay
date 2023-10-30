create or replace PROCEDURE InserirPagamento(
    p_cd_conta IN t_fp_conta.cd_conta%type,
    p_cd_tipo_pagmt IN t_fp_tipo_pagmt.cd_tipo%type,
    p_cd_barras IN t_fp_pagmt.cd_barras%type,
    p_nm_pagmt IN t_fp_pagmt.nm_pagmt%type,
    p_dt_pagmt IN t_fp_pagmt.dt_pagmt%type,
    p_vl_pagmt IN t_fp_pagmt.vl_pagmt%type
)
IS BEGIN
    INSERT INTO t_fp_pagmt
        (
            cd_pagmt,
            t_fp_conta_cd_conta,
            t_fp_tipo_pagmt_cd_tipo,
            cd_barras,
            nm_pagmt,
            dt_pagmt,
            vl_pagmt
        )
        VALUES (
            seq_pagmt.nextval,
            p_cd_conta,
            p_cd_tipo_pagmt,
            p_cd_barras,
            p_nm_pagmt,
            p_dt_pagmt,
            p_vl_pagmt
        );

    COMMIT;
END;