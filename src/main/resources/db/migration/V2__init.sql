create table if not exists WishItem
(id NUMBER,
  name VARCHAR2(32) PRIMARY KEY,
  weight VARCHAR2(32),
  clatters VARCHAR2(32),
  size VARCHAR2(32),
  giver VARCHAR2(32)
)
;

create table if not EXISTS Present
(id NUMBER,
 weight VARCHAR2(32),
 clatters VARCHAR2(32),
 size VARCHAR2(32),
 giver VARCHAR2(32)
)
;

