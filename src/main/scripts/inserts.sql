INSERT INTO ENDERECO (RUA, CIDADE, UF, NUMERO, CEP) VALUES ('Felipe Schimdt', 'Florianópolis', 'SC', '1', '88005444');
INSERT INTO ENDERECO (RUA, CIDADE, UF, NUMERO, CEP) VALUES ('Max Schramm', 'Florianópolis', 'SC', '3', '88005222');
INSERT INTO ENDERECO (RUA, CIDADE, UF, NUMERO, CEP) VALUES ('Presidente Kennedy', 'São José', 'SC', '2', '88222444');
INSERT INTO ENDERECO (RUA, CIDADE, UF, NUMERO, CEP) VALUES ('Nereu Ramos', 'Lages', 'SC', '105', '88555444');

INSERT INTO telefonia.CLIENTE (id,nome, cpf, id_endereco) VALUES (1,'Edson Arantes','11133322211', 2);
INSERT INTO telefonia.CLIENTE (id,nome, cpf, id_endereco) VALUES (2,'Artur Antunes','22233322211', 4);
INSERT INTO telefonia.CLIENTE (id,nome, cpf, id_endereco) VALUES (3,'Marcos André','11133322255', 6);

INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (1,'48','32323232', 0, 1);
INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (2,'48','984213535', 1, 0);
INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (3,'11','77323232', 0, 1);
INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (4,'21','774213535', 1, 0);
INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (5,'51','32623232', 0, 1);
INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (6,'61','985513535', 1, 0);
INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (7,'48','32443232', 0, 1);
INSERT INTO telefonia.TELEFONE (id, ddd, numero, tipo, ativo) VALUES (8,'48','894213535', 1, 0);