-- Para rodar após a execução

INSERT INTO pratos (cod, imagem_caminho, nome, preco) VALUES ('2c383a4f-caa2-4c33-a2ca-537c5d23258f', 'images/2c383a4f-caa2-4c33-a2ca-537c5d23258f.png', 'Teste', 23);
INSERT INTO pratos (cod, imagem_caminho, nome, preco) VALUES ('ff590e08-cb70-42db-a06f-b0a3bb2ce7a1', 'images/ff590e08-cb70-42db-a06f-b0a3bb2ce7a1.png', 'Ronier', 10);

INSERT INTO role (papel) VALUES ('ROLE_ADM');
INSERT INTO role (papel) VALUES ('ROLE_CLIENTE');

--senha 123
INSERT INTO usuario (cpf, data_nascimento, email, endereco, nome, senha) VALUES ('073.788.880-77', '1111-11-11', 'ronier.lim@gmail.com', 'asdasd', 'Ronier', '$2a$12$1XeTsJBNSTulEBaAOZS//e1jc5Z2SDuL3psD6hDSSwUy36/X9QYHe');

INSERT INTO usuarios_roles (usuario_codigo, role_codigo) VALUES (1, 'ROLE_CLIENTE');
INSERT INTO usuarios_roles (usuario_codigo, role_codigo) VALUES (1, 'ROLE_ADM');

