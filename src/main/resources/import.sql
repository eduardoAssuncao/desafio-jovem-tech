-- Seed de Empresa
INSERT INTO tb_empresa (id, nome_fantasia, endereco, email) VALUES (1, 'Empresa A', 'Rua A, 123', 'contato@empresaA.com');

-- Seed de Cliente
INSERT INTO tb_cliente (id, nome, email, cpf, empresa_id) VALUES (1, 'Cliente A', 'clienteA@example.com', '111.111.111-11', 1);
INSERT INTO tb_cliente (id, nome, email, cpf, empresa_id) VALUES (2, 'Cliente B', 'clienteB@example.com', '222.222.222-22', 1);

-- Seed de Produto
INSERT INTO tb_produto (id, nome, descricao, preco, quantidade_estoque, empresa_id) VALUES (1, 'Produto 1', 'Descrição do Produto 1', 100.00, 10, 1);
INSERT INTO tb_produto (id, nome, descricao, preco, quantidade_estoque, empresa_id) VALUES (2, 'Produto 2', 'Descrição do Produto 2', 200.00, 20, 1);

-- Seed de Pedido
INSERT INTO tb_pedido (id, data_pedido, status, client_id) VALUES (1, '2024-07-15T10:00:00Z', 1, 1);
INSERT INTO tb_pedido (id, data_pedido, status, client_id) VALUES (2, '2024-07-15T11:00:00Z', 1, 2);

-- Seed de ItemPedido
INSERT INTO tb_item_pedido (pedido_id, produto_id, quantidade, preco, nome) VALUES (1, 1, 2, 100.00, select nome from tb_produto where id = 1);
INSERT INTO tb_item_pedido (pedido_id, produto_id, quantidade, preco, nome) VALUES (1, 2, 1, 200.00, select nome from tb_produto where id = 2);
INSERT INTO tb_item_pedido (pedido_id, produto_id, quantidade, preco, nome) VALUES (2, 1, 1, 100.00, select nome from tb_produto where id = 1);
