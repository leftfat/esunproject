package com.esunbank.Charlie.backend.common.enumfolder;

import lombok.Getter;

@Getter
public enum Status {

    IN_STOCK(0, "在庫"),
    ON_LOAN(1, "出借中"),
    BEING_SORTED(2, "整理中(歸還後未入庫)"),
    LOST(3, "遺失"),
    DAMAGED(4, "損毀"),
    DISCARDED(5, "廢棄");

    private final Integer code;
    private final String text;

    Status(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

}
