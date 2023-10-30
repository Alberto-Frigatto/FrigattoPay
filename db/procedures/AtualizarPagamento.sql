create or replace PROCEDURE AtualizarPagamento(
    p_cd_pagmt IN t_fp_pagmt.cd_pagmt%type,
    p_cd_tipo_pagmt IN t_fp_tipo_pagmt.cd_tipo%type,
    p_nm_pagmt IN t_fp_pagmt.nm_pagmt%type
)
IS BEGIN
    UPDATE t_fp_pagmt
        SET nm_pagmt = p_nm_pagmt,
            t_fp_tipo_pagmt_cd_tipo = p_cd_tipo_pagmt
        WHERE cd_pagmt = p_cd_pagmt;

    COMMIT;
END;