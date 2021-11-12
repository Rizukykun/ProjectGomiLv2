use master

drop database Gomi

create database Gomi
go

use Gomi

create table Administrador(
	IdAdministrador int primary key identity(1, 1)
)

create table Cliente(
	IdCliente int primary key identity(1, 1),
	Rua varchar(max),
	Numero int,
	Complemento varchar(max),
	Bairro varchar(max),
	Cidade varchar(max),
	CEP varchar(max)
)

create table Motorista(
	IdMotorista int primary key identity(1, 1),
	TipoVeiculo varchar(max),
	CNH varchar(max),
	DataExpiracao date,
	CategoriaCNH varchar(max),
	/* Armazenamento da foto da CHN */
	CargaSuportada int
)
go

create table NaoAdm(
	IdNaoAdm int primary key identity(1, 1),
	IdCliente int foreign key references Cliente(IdCliente) null,
	IdMotorista int foreign key references Motorista(IdMotorista) null,
	/* Informações de pagamento/Cobrança */
	/* Armazenamento da foto */
	TelefoneDDD int,
	Telefone int,
)
go

create table Usuario(
	IdUsuario int primary key identity(1, 1),
	IdNaoAdm int foreign key references NaoAdm(IdNaoAdm),
	IdAdministrador int foreign key references Administrador(IdAdministrador),
	Email varchar(max),
	Senha varchar(max),
	Nome varchar(max),
	DataNascimento date,
	CPF varchar(max)
)
go

create table Solicitacao(
	IdSolicitacao int primary key identity(1, 1),
	IdCliente int foreign key references Cliente (IdCliente),
	IdMotorista int foreign key references Motorista (IdMotorista),
	Coletado bit,
	DataSolicitacao smalldatetime,
	Aberto bit,
	Descricao varchar(max),
	cep varchar(max),
	numero int,
	origem varchar(max)
	--Armazenamento das Fotos
)

create table Categoria(
	idCategoria int primary key identity(1, 1),
	descricaoCategoria varchar(max)
)
go

create table CategoriaSolicitacao(
	IdSolicitacao int foreign key references Solicitacao(IdSolicitacao),
	IdCategoria int foreign key references Categoria(IdCategoria),
	Primary Key (IdSolicitacao, IdCategoria)
)

create table [Log] (
	IdLog int primary key identity(1, 1),
	Descricao varchar(max)
)

create table Ecoponto (
	IdEcoponto int primary key identity(1, 1),
	cep varchar(max),
	numero int
)
go

create table Sessao (
	HashSessao varchar(256) primary key,
	TempoLimite datetime2,
	IdCliente int foreign key references Cliente(IdCliente)
)
go

create procedure spConsult (@id int ,@tabela varchar(max)) as
begin
	declare @sql varchar(max);
	set @sql = 'select * from ' + @tabela +
			   ' where id' + @tabela + ' = ' + cast(@id as varchar(max))
	exec(@sql)
end
go

create procedure spDelete (@id int, @tabela varchar(max))
as
begin
    declare @sql varchar(max)
	set @sql = 'delete ' + @tabela + ' where id' + @tabela + ' = ' + convert(varchar(max),@id)
	exec (@sql)	
end
go

create procedure spList (@tabela varchar(max),@ordem varchar(max)) as
begin
	exec('select * from ' + @tabela +
		 ' order by ' + @ordem)
end
go

/*Criação das procedures de Insert e Update*/

create procedure spInsert_Log (@Id int, @Descricao varchar(max)) as
begin
	insert into [Log] values (@Descricao)
	select @@IDENTITY as 'Id'
end
go

create procedure spInsert_Solicitacao (@IdSolicitacao int, @IdCliente int, @IdMotorista int, @Coletado bit, @DataSolicitacao varchar(max), @Aberto bit, @Descricao varchar(max), @CEP varchar(max), @numero int, @origem varchar(max)) as
begin
	declare @date varchar(max)
	set @date = convert(smalldatetime, @DataSolicitacao, 105)
	
	insert into Solicitacao values (@IdCliente, @IdMotorista, @Coletado, @date, @Aberto, @Descricao, @CEP, @numero, @origem)
	select @@IDENTITY as 'Id'
end
go

create procedure spUpdate_Solicitacao (@IdSolicitacao int, @IdCliente int, @IdMotorista int, @Coletado bit, @DataSolicitacao varchar(max), @Aberto bit, @Descricao varchar(max), @CEP varchar(max), @numero int, @origem varchar(max)) as
begin
	declare @date varchar(max)
	set @date = convert(smalldatetime, @DataSolicitacao, 105)
	
	update Solicitacao set IdCliente = @IdCliente, 
						   IdMotorista = @IdMotorista, 
						   Coletado = @Coletado, 
						   DataSolicitacao = @date,
						   Aberto = @Aberto,
						   Descricao = @Descricao,
						   cep = @CEP,
						   numero = @numero,
						   origem = @origem
						   where IdSolicitacao = @IdSolicitacao
end
go

create procedure spInsert_Notificacao (@IdNotificacao int, @IdSolicitacao int, @IdUsuario int, @Mensagem varchar(max)) as
begin
	insert into Notificacao values (@IdSolicitacao, @IdUsuario, @Mensagem)
	select @@IDENTITY as 'Id'
end
go

create procedure spInsert_Administrador (@IdAdministrador int) as
begin
	insert into Administrador default values
	select @@IDENTITY as 'Id'
end
go

/*
create procedure spUpdate_Administrador (@IdAdministrador int) as
begin
	Update Administrador set ?
	where IdAdministrador = @IdAdministrador
end
go
*/


create procedure spInsert_Cliente (@IdCliente int, @Rua varchar(max), @Numero int, @Complemento varchar(max), @Bairro varchar(max), @Cidade varchar(max), @CEP varchar(max)) as
begin
	insert into Cliente values (@rua, @Numero, @Complemento, @Bairro, @Cidade, @CEP)
	select @@IDENTITY as 'Id'
end
go

create procedure spUpdate_Cliente (@IdCliente int, @Rua varchar(max), @Numero int, @Complemento varchar(max), @Bairro varchar(max), @Cidade varchar(max), @CEP varchar(max)) as
begin
	update Cliente set Rua = @Rua,
					   Numero = @Numero,
					   Complemento = @Complemento,
					   Bairro = @Bairro,
					   Cidade = @Cidade,
					   CEP = @CEP
	where IdCliente = @IdCliente
end
go

create procedure spInsert_Motorista (@IdMotorista int, @tipoVeiculo varchar(max), @Cnh varchar(max), @DataExpiracao date, @CategoriaCnh varchar(max), @cargaSuportada int) as
begin
	insert into Motorista values (@tipoVeiculo, @Cnh, @DataExpiracao, @CategoriaCnh, @cargaSuportada)
	select @@IDENTITY as 'Id'
end
go

create procedure spUpdate_Motorista (@IdMotorista int, @tipoVeiculo varchar(max), @Cnh varchar(max), @DataExpiracao date, @CategoriaCnh varchar(max), @cargaSuportada int) as
begin
	Update Motorista set TipoVeiculo = @tipoVeiculo,
						 CNH = @Cnh,
						 DataExpiracao = @DataExpiracao,
						 CategoriaCNH = @CategoriaCnh,
						 CargaSuportada = @cargaSuportada
						 where IdMotorista = @IdMotorista
end
go

create procedure spInsert_NaoAdm (@IdNaoAdm int, @IdCliente int, @IdMotorista int, @TelefoneDDD int, @Telefone int) as
begin
	insert into NaoAdm values (@IdCliente, @IdMotorista, @TelefoneDDD, @Telefone)
	select @@IDENTITY as 'Id'
end
go

create procedure spUpdate_NaoAdm (@IdNaoAdm int, @IdCliente int, @IdMotorista int, @TelefoneDDD int, @Telefone int) as
begin
	update NaoAdm set IdCliente = @IdCliente,
					  IdMotorista = @IdMotorista,
					  TelefoneDDD = @TelefoneDDD,
					  Telefone = @Telefone
					  where IdNaoAdm = @IdNaoAdm
end
go

create procedure spInsert_Usuario (@IdUsuario int, @IdNaoAdm int, @IdAdministrador int, @Email varchar(max), @Senha varchar(max), @Nome varchar(max), @DataNascimento date, @CPF bigint) as
begin
	insert into Usuario values (@IdNaoAdm, @IdAdministrador, @Email, @Senha, @Nome, @DataNascimento, @CPF)
	select @@IDENTITY as 'Id'
end
go

create procedure spUpdate_Usuario (@IdUsuario int, @IdNaoAdm int, @IdAdministrador int, @Email varchar(max), @Senha varchar(max), @Nome varchar(max), @DataNascimento date, @CPF bigint) as
begin
	update Usuario set IdNaoAdm = @IdNaoAdm,
					   IdAdministrador = @IdAdministrador,
					   Email = @Email,
					   Senha = @Senha,
					   Nome = @Nome,
					   DataNascimento = @DataNascimento,
					   CPF = @CPF
					   where IdUsuario = @IdUsuario
end
go

create procedure spInsert_Ecoponto (@idEcoponto int, @cep varchar(max), @numero int) as
begin
	insert into Ecoponto values (@cep, @numero)
	select @@IDENTITY as 'Id'
end
go

create procedure spUpdate_Ecoponto (@idEcoponto int, @cep varchar(max), @numero int) as
begin
	update Ecoponto set cep = @cep,
						numero = @numero
end
go

create procedure spInsert_Categoria (@idCategoria int, @descricaoCategoria varchar(max)) as
begin
	insert into Categoria values (@descricaoCategoria)
    select @@IDENTITY as 'Id'
end
go

create procedure spUpdate_Categoria (@idCategoria int, @descricaoCategoria varchar(max)) as
begin
	update Categoria set descricaoCategoria = @descricaoCategoria
end
go

create procedure spInsert_CategoriaSolicitacao (@IdSolicitacao int, @IdCategoria int) as
begin
	insert into CategoriaSolicitacao values (@IdSolicitacao, @IdCategoria)
    select @@IDENTITY as 'Id'
end
go

create procedure spDelete_CategoriaSolicitacao (@IdSolicitacao int, @IdCategoria int) as
begin
	delete CategoriaSolicitacao where idSolicitacao = @IdSolicitacao and IdCategoria = @IdCategoria
	
end
go

create procedure spLogin (@usuario varchar(max), @senha varchar(max)) as
begin
	select * from Usuario where Email = @usuario and Senha = @senha
end
go

create procedure spUsuario (@usuario varchar(max)) as
begin
	select* from Usuario where email = @usuario
end
go

create procedure spConsultMotorista (@id int) as
begin
	select u.*, na.IdCliente, na.Telefone, na.TelefoneDDD, m.* from Motorista m
	inner join
	NaoAdm na on m.IdMotorista = na.IdMotorista
	inner join
	Usuario u on na.IdNaoAdm = u.IdNaoAdm
	where m.IdMotorista = @id
end
go

create procedure spListarSolicitacoesAbertas as
begin
	select * from Solicitacao where Aberto = 1 and IdMotorista is null
end
go

create procedure spInsert_Sessao (@HashSessao varchar(256), @TempoLimite datetime2, @IdCliente int) as
begin
	insert into Sessao values (@HashSessao, @TempoLimite, @IdCliente)

	select 0.0 as 'Id'
end
go