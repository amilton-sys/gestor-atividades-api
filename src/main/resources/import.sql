INSERT INTO participante (bairro, complemento, email, nome, numero, telefone) VALUES ( 'Centro', 'Apto 103', 'jonas.silva@email.com', 'Jonas Silva', '123', '555-1236');
INSERT INTO participante (bairro, complemento, email, nome, numero, telefone) VALUES ( 'Centro', 'Apto 101', 'joao.silva@email.com', 'João Silva', '123', '555-1234');
INSERT INTO participante (bairro, complemento, email, nome, numero, telefone) VALUES ( 'Centro', 'Apto 102', 'jose.silva@email.com', 'José Silva', '123', '555-1235');

INSERT INTO recurso (nome, tipo, disponivel) VALUES ( 'Projetor', 'Equipamento',false);
INSERT INTO recurso (nome, tipo, disponivel) VALUES ( 'Notebook', 'Equipamento',true);
INSERT INTO recurso (nome, tipo,disponivel) VALUES  ( 'Quadro branco', 'Equipamento',false);
INSERT INTO recurso (nome, tipo,disponivel) VALUES  ( 'Quadro negro', 'Equipamento', true);

INSERT INTO atividade (data_fim, data_inicio, nome) VALUES ('2024-09-30', '2024-09-01', 'Reunião de Planejamento');

INSERT INTO atividade_participante (atividade_id, participante_id) VALUES (1,1);
INSERT INTO atividade_participante (atividade_id, participante_id) VALUES (1,2);

INSERT INTO atividade_recurso (atividade_id, recurso_id) VALUES (1,4)

