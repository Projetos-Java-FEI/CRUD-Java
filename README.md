https://excalidraw.com/#json=AiG6720xj1HsRhVjoXsvR,XszVlpz-nF5W5ycclkdM3g


# Script do Banco de dados
-- Criação da tabela criptos com taxas de compra e venda separadas
CREATE TABLE criptos (
    simbolo VARCHAR(10) PRIMARY KEY,  
    nome VARCHAR(50) NOT NULL,
    cotacao DECIMAL(20, 8) NOT NULL,
    taxa_compra DECIMAL(5, 2) NOT NULL,  -- Taxa de compra da criptomoeda
    taxa_venda DECIMAL(5, 2) NOT NULL,   -- Taxa de venda da criptomoeda
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criação da tabela users
CREATE TABLE users (
    id_user SERIAL PRIMARY KEY,  -- Identificador único do usuário
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,  -- CPF único
    senha TEXT NOT NULL,  -- Senha do usuário
    user_type VARCHAR(50) NOT NULL  -- Tipo de usuário (admin, comum, etc.)
);

-- Nova estrutura da tabela carteira, com um registro por criptomoeda
CREATE TABLE carteira (
    id_carteira SERIAL PRIMARY KEY,  -- Identificador único da carteira
    id_user INT NOT NULL,  -- Chave estrangeira que referencia a tabela users
    simbolo VARCHAR(10) NOT NULL,  -- Símbolo da criptomoeda
    saldo DECIMAL(20, 8) DEFAULT 0.0,  -- Saldo inicial definido para zero
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE,  -- Exclusão em cascata com users
    FOREIGN KEY (simbolo) REFERENCES criptos(simbolo) ON DELETE CASCADE  -- Exclusão em cascata com criptos
);

-- Atualização da tabela extrato com exclusão em cascata
CREATE TABLE extrato (
    id_extrato SERIAL PRIMARY KEY,  -- Identificador único do extrato
    id_user INT NOT NULL,  -- Referência à tabela users
    quantidade DECIMAL(20, 8) NOT NULL,  -- Quantidade de criptomoeda no extrato
    operacao_reais VARCHAR(20) NOT NULL,
    tipo_operacao VARCHAR(20) NOT NULL,  -- Tipo da operação (compra/venda, etc.)
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data da operação
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE  -- Exclusão em cascata com users
);

-- Criação da tabela historico_cripto
CREATE TABLE historico_cripto (
    id_historico SERIAL PRIMARY KEY,  -- Identificador único do histórico de cotação
    simbolo VARCHAR(5) NOT NULL,  -- Símbolo da criptomoeda
    cotacao DECIMAL(20, 8) NOT NULL,  -- Cotação da criptomoeda no momento do registro
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data e hora do registro da cotação
    FOREIGN KEY (simbolo) REFERENCES criptos(simbolo) ON DELETE CASCADE  -- Relacionamento com a tabela criptos com CASCADE
);

-- Índices para melhorar o desempenho nas consultas
CREATE INDEX idx_users_cpf ON users(cpf);
CREATE INDEX idx_criptos_simbolo ON criptos(simbolo);
CREATE INDEX idx_extrato_id_user ON extrato(id_user);
CREATE INDEX idx_historico_cripto_simbolo ON historico_cripto(simbolo);
CREATE INDEX idx_carteira_user_simbolo ON carteira(id_user, simbolo);

-- Inserção das moedas fundamentais na tabela criptos com taxas de compra e venda
INSERT INTO criptos (simbolo, nome, cotacao, taxa_compra, taxa_venda)
VALUES
('BTC', 'Bitcoin', 30000.00, 0.02, 0.015),  -- Exemplo de taxa de compra e venda
('ETH', 'Ethereum', 2000.00, 0.02, 0.015),
('XRP', 'Ripple', 0.50, 0.02, 0.015),
('BRL', 'Reais', 1.00, 0.00, 0.00);  -- Real com taxa zero para compra e venda

-- Inserção de usuários na tabela users
INSERT INTO users (nome, cpf, senha, user_type)
VALUES
('Admin', '1', 'adm123', 'Administrador'),
('dev', '2', 'dev123', 'Investidor');  

-- Inserção de saldos iniciais na tabela carteira para cada usuário criado
INSERT INTO carteira (id_user, simbolo, saldo)
VALUES
(1, 'BTC', 0.0), (1, 'ETH', 0.0), (1, 'XRP', 0.0), (1, 'BRL', 0.0),  -- Carteira do usuário 'Admin'
(2, 'BTC', 0.0), (2, 'ETH', 0.0), (2, 'XRP', 0.0), (2, 'BRL', 0.0);  -- Carteira do usuário 'dev'

