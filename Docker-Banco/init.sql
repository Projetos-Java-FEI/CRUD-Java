CREATE TABLE criptos (
    simbolo VARCHAR(10) PRIMARY KEY,  
    nome VARCHAR(50) NOT NULL,
    cotacao DECIMAL(20, 8) NOT NULL,
    taxa_compra DECIMAL(5, 2) NOT NULL,
    taxa_venda DECIMAL(5, 2) NOT NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    id_user SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    senha TEXT NOT NULL,
    user_type VARCHAR(50) NOT NULL
);

CREATE TABLE carteira (
    id_carteira SERIAL PRIMARY KEY,
    id_user INT NOT NULL,
    simbolo VARCHAR(10) NOT NULL,
    saldo DECIMAL(20, 8) DEFAULT 0.0,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE,
    FOREIGN KEY (simbolo) REFERENCES criptos(simbolo) ON DELETE CASCADE
);

CREATE TABLE extrato (
    id_extrato SERIAL PRIMARY KEY,
    id_user INT NOT NULL,
    quantidade DECIMAL(20, 8) NOT NULL,
    operacao_reais VARCHAR(20) NOT NULL,
    tipo_operacao VARCHAR(20) NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);

CREATE TABLE historico_cripto (
    id_historico SERIAL PRIMARY KEY,
    simbolo VARCHAR(5) NOT NULL,
    cotacao DECIMAL(20, 8) NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (simbolo) REFERENCES criptos(simbolo) ON DELETE CASCADE
);

CREATE INDEX idx_users_cpf ON users(cpf);
CREATE INDEX idx_criptos_simbolo ON criptos(simbolo);
CREATE INDEX idx_extrato_id_user ON extrato(id_user);
CREATE INDEX idx_historico_cripto_simbolo ON historico_cripto(simbolo);
CREATE INDEX idx_carteira_user_simbolo ON carteira(id_user, simbolo);

INSERT INTO criptos (simbolo, nome, cotacao, taxa_compra, taxa_venda)
VALUES
    ('BTC', 'Bitcoin', 30000.00, 0.02, 0.015),
    ('ETH', 'Ethereum', 2000.00, 0.02, 0.015),
    ('XRP', 'Ripple', 0.50, 0.02, 0.015),
    ('BRL', 'Reais', 1.00, 0.00, 0.00);

INSERT INTO users (nome, cpf, senha, user_type)
VALUES
    ('Admin', '1', '$2a$10$tFjzacPdT0Tn4nBlCKodduMDW0cxgb3a/rUStmK9HYsEpFEu2tQim', 'Administrador'),
    ('dev', '2', '$2a$10$3iX2Dmr946PVm9Wv4srrQuZtmDi5NVvfxp7KkUCAGAmkfj/6GsHNq', 'Investidor');

INSERT INTO carteira (id_user, simbolo, saldo)
VALUES
    (1, 'BTC', 0.0), (1, 'ETH', 0.0), (1, 'XRP', 0.0), (1, 'BRL', 0.0),
    (2, 'BTC', 0.0), (2, 'ETH', 0.0), (2, 'XRP', 0.0), (2, 'BRL', 0.0);
