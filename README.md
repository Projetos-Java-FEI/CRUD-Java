https://excalidraw.com/#json=AiG6720xj1HsRhVjoXsvR,XszVlpz-nF5W5ycclkdM3g


# Script do Banco de dados
-- Criação da tabela criptos
CREATE TABLE criptos (
    simbolo VARCHAR(10) PRIMARY KEY,  
    nome VARCHAR(50) NOT NULL,
    cotacao DECIMAL(20, 8) NOT NULL,
    tax DECIMAL(5, 2) NOT NULL,
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

-- Criação da tabela carteira
CREATE TABLE carteira (
    id_carteira SERIAL PRIMARY KEY,  -- Identificador único da carteira
    id_user INT NOT NULL,  -- Chave estrangeira que referencia a tabela users
    saldo_real DECIMAL(20, 8) DEFAULT 0.0,  -- Saldo inicial definido para zero
    saldo_BTC DECIMAL(20, 8) DEFAULT 0.0,
    saldo_ETH DECIMAL(20, 8) DEFAULT 0.0,
    saldo_XRP DECIMAL(20, 8) DEFAULT 0.0,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE  -- Relacionamento com a tabela users com exclusão em cascata
);

-- Criação da tabela extrato
CREATE TABLE extrato (
    id_extrato SERIAL PRIMARY KEY,  -- Identificador único do extrato
    id_user INT NOT NULL,  -- Referência à tabela users
    quantidade DECIMAL(20, 8) NOT NULL,  -- Quantidade de criptomoeda no extrato
    operacao_reais VARCHAR(20) NOT NULL,
    tipo_operacao VARCHAR(20) NOT NULL,  -- Tipo da operação (compra/venda, etc.)
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data da operação
    FOREIGN KEY (id_user) REFERENCES users(id_user)  -- Relacionamento com a tabela users sem exclusão em cascata
);

-- Criação da tabela historico_cripto
CREATE TABLE historico_cripto (
    id_historico SERIAL PRIMARY KEY,  -- Identificador único do histórico de cotação
    simbolo VARCHAR(5) NOT NULL,  -- Símbolo da criptomoeda
    cotacao DECIMAL(20, 8) NOT NULL,  -- Cotação da criptomoeda no momento do registro
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data e hora do registro da cotação
    FOREIGN KEY (simbolo) REFERENCES criptos(simbolo)  -- Relacionamento com a tabela criptos
);

-- Índices para melhorar o desempenho nas consultas
CREATE INDEX idx_users_cpf ON users(cpf);
CREATE INDEX idx_carteira_id_carteira ON carteira(id_carteira);
CREATE INDEX idx_criptos_simbolo ON criptos(simbolo);
CREATE INDEX idx_extrato_id_user ON extrato(id_user);
CREATE INDEX idx_historico_cripto_simbolo ON historico_cripto(simbolo);

-- Inserção das moedas fundamentais na tabela criptos
INSERT INTO criptos (simbolo, nome, cotacao, tax)
VALUES
('BTC', 'Bitcoin', 30000.00, 0.02),  -- Cotação e taxa de exemplo
('ETH', 'Ethereum', 2000.00, 0.02),
('XRP', 'Ripple', 0.50, 0.02),
('BRL', 'Reais', 1.00, 0.00);  -- Real com taxa 0 e cotação como moeda base

-- Inserção de usuários na tabela users
INSERT INTO users (nome, cpf, senha, user_type)
VALUES
('Admin', '1', 'adm123', 'Administrador'),
('dev', '2', 'dev123', 'Investidor');  

-- Inserção de carteiras para cada usuário criado
INSERT INTO carteira (id_user, saldo_real, saldo_BTC, saldo_ETH, saldo_XRP)
VALUES
(1, 0.0, 0.0, 0.0, 0.0),  -- Carteira do usuário 'Admin'
(2, 0.0, 0.0, 0.0, 0.0);  -- Carteira do usuário 'dev'
