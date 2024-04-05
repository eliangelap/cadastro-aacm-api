merge into ger_estado 
    (id_estado, nm_estado, nr_ibge, cd_uf) 
values
    (1 , 'Acre', 12, 'AC'),
    (2 , 'Alagoas', 27, 'AL'),
    (3 , 'Amapá', 16, 'AP'),
    (4 , 'Amazonas', 13, 'AM'),
    (5 , 'Bahia', 29, 'BA'),
    (6 , 'Ceará', 23, 'CE'),
    (7 , 'Distrito Federal', 53, 'DF'),
    (8 , 'Espírito Santo', 32, 'ES'),
    (9 , 'Goiás', 52, 'GO'),
    (10 , 'Maranhão', 21, 'MA'),
    (11 , 'Mato Grosso', 51, 'MT'),
    (12 , 'Mato Grosso do Sul', 50, 'MS'),
    (13 , 'Minas Gerais', 31, 'MG'),
    (14 , 'Pará', 15, 'PA'),
    (15 , 'Paraíba', 25, 'PB'),
    (16 , 'Paraná', 41, 'PR'),
    (17 , 'Pernambuco', 26, 'PE'),
    (18 , 'Piauí', 22, 'PI'),
    (19 , 'Rio Grande do Norte', 24, 'RN'),
    (20 , 'Rio Grande do Sul', 43, 'RS'),
    (21 , 'Rio de Janeiro', 33, 'RJ'),
    (22 , 'Rondônia', 11, 'RO'),
    (23 , 'Roraima', 14, 'RR'),
    (24 , 'Santa Catarina', 42, 'SC'),
    (25 , 'São Paulo', 35, 'SP'),
    (26 , 'Sergipe', 28, 'SE'),
    (27 , 'Tocantins', 17, 'TO');

merge into ger_municipio
    (id_municipio, nm_municipio, nr_ibge, id_estado) 
values
    (1, 'Campo Mourão', 4104303, 16);

merge into ger_endereco
    (id_endereco, tp_endereco, ds_logradouro, ds_bairro, ds_complemento, nr_cep, id_municipio) 
values
    (1, 'HOME', 'Rua Teste dos Testes', 'Jardim Teste', null, '87303000', 1);

merge into pes_pessoa
    (id_pessoa, id_endereco, ds_email, dt_cadastro) 
values
    (1, 1, 'eliangela@eliangela.dev.br', '2024-03-30');

merge into pes_pessoafisica
    (id_pessoa, nm_pessoa, nr_cpf, nr_rg, dt_nascimento, tp_sexo) 
values
    (1, 'Eliangela M P P', '01234567890', '0987654321', '1984-01-01', 'FEMALE');

merge into seg_usuario
    (id_usuario, id_pessoa, ds_email, ds_senha, in_cancelado, dt_cadastro) 
values
    (1, 1, 'eliangela@eliangela.dev.br', '$2a$12$r2IFCsBfn2hMiWES.MuVy.s7L1fT699iQryITSArdydNNupqJD23C', 'FALSE', '2024-03-30');