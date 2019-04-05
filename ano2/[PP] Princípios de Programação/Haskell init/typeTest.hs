--Definindo novos tipos com type - Projeto 08
type Nome = String
type Idade = Int
type Sexo = String
type Pessoa = (Nome, Idade, Sexo)

pessoa :: Pessoa
pessoa = ("Ruhan", 20, "M")

my_fst :: Pessoa -> Nome
my_fst (name, age, sex) = name
my_snd :: Pessoa -> Idade
my_snd (name, age, sex) = age
my_ter :: Pessoa -> Sexo
my_ter (name, age, sex) = sex