## CriptoFEI

Bem-vindo ao projeto CriptoFEI! Esta aplicação foi desenvolvida em Java e SQL, como projeto para a matéria de Orientação à Objetos do 3o semestre de Ciência da Computação da FEI. <br>
Essa aplicação têm com foco oferecer uma interface de troca de criptomoedas tanto para administradores quanto para usuários. O sistema permite que administradores gerenciem criptomoedas, visualizem extratos de clientes e criem novas criptomoedas. Os usuários podem adicionar reais à sua conta, comprar criptomoedas pré-programadas(BTC, ETH e XRP) ou aquelas criadas pelo Administrador. A aplicação também exibe gráficos para mostrar a divisão do saldo do usuário em diferentes criptomoedas.

## Funcionalidades

### Para Administradores:
- **Cadastrar Investidores**: Cadastrar novos investidores, com todos os dados necessários.
- **Consultar Saldo**: Consultar os dados de algum investidor, tais como: nome, CPF, e os seus saldos.
- **Excluir Investidores**: Excluir investidores desejados, usando o CPF do usuário e a senha de administrador.
- **Visualizar Extratos de Clientes**: Ver o histórico de transações de todos os usuários e seus saldos.
- **Gerenciar Criptomoedas**: Criar novas criptomoedas e definir suas propriedades (como nome, símbolo, cotação e taxas de compra e venda).
- **Atualizar Cotação**: Administradores podem atualizar a cotação de forma aleatória (-5% até 5% por clique).

### Para Usuários:
- **Depositar Reais**: Os usuários podem adicionar reais à sua conta, convertendo-os em saldo.
- **Sacar Reais**: Os usuários podem sacar os reais de sua própria conta.
- **Comprar Criptomoedas**: Comprar criptomoedas predefinidas ou as que foram criadas pelo administrador.
- **Vender Criptomoedas**: Vender criptomoedas predefinidas ou as que foram criadas pelo administrador.
- **Ver Extrato**: É possível ver o extrato de forma amigável e de fácil entendimento no menu carteira.
- **Gráficos de Renda**: Visualizar gráficos de pizza mostrando a divisão do saldo do usuário entre as criptomoedas que ele possui.
- **Senhas Criptografadas**: O sistema armazena senhas de usuários de forma segura, utilizando criptografia no banco de dados.

### Representação da tela da carteira do usuário: <br>

![image](https://github.com/user-attachments/assets/d5d7a3ba-5f23-4074-bbd4-728f345bef73)
<br>
### Representação da tela de Administrador: <br>
![image](https://github.com/user-attachments/assets/81aa5725-418c-413f-8344-e33bdc4fd777)
<br>
## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Swing**: Biblioteca para construção da interface gráfica.
- **JFreeChart**: Biblioteca para criar gráficos e visualizações.
- **PostgreSQL**: Banco de dados utilizado para armazenar os dados de usuários, criptomoedas, transações e histórico das moedas.
- **JBCrypt**: Para gerenciamento e criptografia de senhas.

## Funcionalidades Extras

- **Criptografia de Senhas**: Senhas dos usuários são armazenadas de forma segura no banco de dados usando criptografia.
- **Interação em Tempo Real**: A interface gráfica se atualiza automaticamente ao realizar transações ou modificações na conta.

## Como Executar

1. Clone este repositório.
2. Compile o projeto.
3. Certifique-se que o script do banco está atualizado.
4. Execute o .jar.

---

## Opcional: Testar o Banco de Dados com Docker

### Requisitos
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/) instalados.

### Inicializar o Banco de Dados
1. Navegue até o diretório `Docker-Banco`:  
   ```bash
   cd Docker-Banco
2. Suba o container com o banco configurado:
   ```bash
   docker-compose up -d
3. Verifique se o container está ativo:
   ```bash
   docker ps
### Testar o Banco de Dados
1. Acesse o banco via terminal:
   ```bash
   docker exec -it postgres_16 psql -U postgres -d CriptoFEI
2. Conectar com o banco CriptoFEI:
   Depois de acessar o banco, no próprio psql utilizar o comando
   ```psql
   \c CriptoFEI
3. Realize consultas, por exemplo, listar as criptomoedas:
   ```psql
   SELECT * FROM criptos;
### Encerrar o banco de dados
1. Execute o seguinte comando para parar o container:
   ```bash
   docker-compose down
### Observações
- Certifique-se de que a porta `5432` esteja disponível em seu ambiente local.
- Caso encontre problemas, abra uma *issue* neste repositório ou consulte a [documentação oficial do Docker](https://docs.docker.com/).





