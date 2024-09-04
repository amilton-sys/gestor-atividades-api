INSERT INTO atividade (id, data_fim, data_inicio, nome) VALUES (1, '2024-09-30', '2024-09-01', 'Reunião de Planejamento');

-- Inserir registros na tabela 'participante'
INSERT INTO participante (id, bairro, complemento, email, nome, numero, telefone, atividade_id) VALUES (1, 'Centro', 'Apto 101', 'joao.silva@email.com', 'João Silva', '123', '555-1234', 1);
INSERT INTO participante (id, bairro, complemento, email, nome, numero, telefone, atividade_id) VALUES (2, 'Centro', 'Apto 102', 'jose.silva@email.com', 'José Silva', '123', '555-1235', null);
INSERT INTO participante (id, bairro, complemento, email, nome, numero, telefone, atividade_id) VALUES (3, 'Centro', 'Apto 103', 'jonas.silva@email.com', 'Jonas Silva', '123', '555-1236', null);

-- Inserir registros na tabela 'recurso'
INSERT INTO recurso (id, nome, tipo, atividade_id) VALUES (1, 'Projetor', 'Equipamento',1);
INSERT INTO recurso (id, nome, tipo, atividade_id) VALUES (2, 'Notebook', 'Equipamento',null);
INSERT INTO recurso (id, nome, tipo, atividade_id) VALUES (3, 'Quadro branco', 'Equipamento',1);




