package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 *  MNO                                       NOT NULL NUMBER
    TITLE                                     NOT NULL VARCHAR2(2000)
    SINGER                                    NOT NULL VARCHAR2(2000)
    ALBUM                                              VARCHAR2(2000)
    POSTER                                    NOT NULL VARCHAR2(300)
    STATE                                              CHAR(12)
    IDCREMENT                                          NUMBER
 */
@Getter
@Setter
public class MusicVO {
    private int mno,idcrement;
    private String title,singer,album,poster,state;
}
