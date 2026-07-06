📖 Sobre o projeto

Este projeto foi desenvolvido em Java com o objetivo de simular o gerenciamento de uma biblioteca digital. 
A aplicação permite cadastrar livros e usuários, realizar empréstimos e devoluções, consultar informações e persistir os dados em arquivos utilizando serialização.

O projeto foi desenvolvido para praticar conceitos fundamentais de Programação Orientada a Objetos e recursos avançados da linguagem Java.

✨ Funcionalidades

    Gestão de Livros
    Cadastro de livros
    Listagem de todos os livros
    Busca de livros por título
    Busca de livros por autor
    Ordenação por título
    Ordenação por ano de publicação
    Listagem de livros disponíveis
    Listagem de livros emprestados
    Gestão de Usuários
    Cadastro de usuários
    Listagem de usuários
    Busca por e-mail
    Empréstimos
    Realização de empréstimos
    Devolução de livros
    Histórico completo de empréstimos com data e hora
    Persistência de Dados
    Salvamento automático em arquivo .dat
    Carregamento automático dos dados ao iniciar a aplicação


🛠 Tecnologias utilizadas

    Java
    Programação Orientada a Objetos (POO)
    Collections (List)
    Generics
    Streams e Expressões Lambda
    Comparable e Comparator
    Optional
    Exceções Personalizadas
    Serialização de Objetos
    Manipulação de Arquivos
    LocalDate e LocalDateTime


📂 Estrutura do Projeto

    src
    │
    ├── app
    │   └── Main.java
    │
    ├── exception
    │   ├── LivroIndisponivelException.java
    │   ├── LivroNaoEncontradoException.java
    │   └── UsuarioNaoEncontradoException.java
    │
    ├── model
    │   ├── Livro.java
    │   ├── Usuario.java
    │   └── Emprestimo.java
    │
    ├── service
    │   ├── ArquivoService.java
    │   ├── BibliotecaService.java
    │   └── DadosBiblioteca.java
    │
    └── util
        └── Menu.java

    
▶️ Como executar

    Clone o repositório.
    Abra o projeto em uma IDE Java (IntelliJ IDEA, Eclipse ou NetBeans).
    Execute a classe Main.java.
    Utilize o menu interativo para gerenciar a biblioteca.

    
🎯 Conceitos aplicados

Este projeto demonstra a utilização de diversos recursos da linguagem Java, entre eles:

Encapsulamento
  
    Classes e Objetos
    Coleções Genéricas
    Streams
    Expressões Lambda
    Comparable e Comparator
    Tratamento de Exceções
    Serialização
    Persistência de Dados
    Organização em camadas (Model, Service, Exception, Util e App)

    
👨‍💻 Autor

    Desenvolvido por Rodrigo Marques Viana como projeto de estudos em Java.
