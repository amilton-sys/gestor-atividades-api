INSERT INTO participante (id, bairro, complemento, email, nome, numero, telefone) VALUES (3, 'Centro', 'Apto 103', 'jonas.silva@email.com', 'Jonas Silva', '123', '555-1236');
INSERT INTO participante (id, bairro, complemento, email, nome, numero, telefone) VALUES (1, 'Centro', 'Apto 101', 'joao.silva@email.com', 'João Silva', '123', '555-1234');
INSERT INTO participante (id, bairro, complemento, email, nome, numero, telefone) VALUES (2, 'Centro', 'Apto 102', 'jose.silva@email.com', 'José Silva', '123', '555-1235');

INSERT INTO recurso (id, nome, tipo, disponivel) VALUES (1, 'Projetor', 'Equipamento',false);
INSERT INTO recurso (id, nome, tipo, disponivel) VALUES (2, 'Notebook', 'Equipamento',true);
INSERT INTO recurso (id, nome, tipo,disponivel) VALUES (3, 'Quadro branco', 'Equipamento',false);

INSERT INTO atividade (id, data_fim, data_inicio, nome) VALUES (1, '2024-09-30', '2024-09-01', 'Reunião de Planejamento');

INSERT INTO atividade_participante (atividade_id, participante_id) VALUES (1,1);

INSERT INTO atividade_recurso (atividade_id, recurso_id) VALUES (1,1)

