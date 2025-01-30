INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');

INSERT INTO tb_usuario (username, password) VALUES ('ADMIN', '$2a$12$5PCDQzxw2MBKb061a4z2nuj5.yrDFuZ9a8HwLMdQp704r2v7eGvPK');
INSERT INTO tb_usuario (username, password) VALUES ('OPERATOR', '$2a$12$V5qsD0/fBlLOmB5mO2xOP./rWcljbawQ0u0gjVP8PeA9TkUL3T9Dm');

INSERT INTO tb_usuario_role VALUES (1,1);
INSERT INTO tb_usuario_role VALUES (2,2);