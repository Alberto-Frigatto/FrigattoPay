package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.dao.DAOClientePF;
import com.dao.DAOClientePJ;
import com.dao.DAODespesa;
import com.dao.DAOReceita;
import com.entity.Receita;
import com.entity.Despesa;
import com.entity.Endereco;
import com.entity.Telefone;
import com.entity.cliente.ClientePF;
import com.entity.cliente.ClientePJ;

public class App
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "RM97807",
                "270205"
            );

            ClientePF gustavo = new ClientePF(
                null,
                "Gustavo Alcântara",
                "guh@gmail.com",
                "admin",
                "12345678912",
                "12345678912",
                "05-05-2002"
            );
            gustavo.addTelefone(new Telefone(null, null, "14256432", null, 11, 3));
            gustavo.addTelefone(new Telefone(null, null, "487512366", null, 11, 3));
            gustavo.addEndereco(new Endereco(null, null, "37580000", "Rua Tubarão na Lagoa", 69, null, 3, 3));

            ClientePF robertoCarlos = new ClientePF(
                null,
                "Roberto Carlos",
                "roberto@gmail.com",
                "perna de pau",
                "32165498744",
                "32165498745",
                "05-05-1950"
            );
            robertoCarlos.addTelefone(new Telefone(null, null, "754124586", null, 11, 3));
            robertoCarlos.addEndereco(new Endereco(null, null, "14201530", "Rua Jabuti na Obra", 678, null, 3, 3));

            ClientePF fabioJunior = new ClientePF(
                null,
                "Fabio Junior",
                "fabio@gmail.com",
                "metades da laranja",
                "45678912352",
                "12785236974",
                "02-02-1962"
            );
            fabioJunior.addTelefone(new Telefone(null, null, "123654789", null, 11, 3));
            fabioJunior.addEndereco(new Endereco(null, null, "12387965", "Rua Marreco Voador", 22, null, 3, 3));
            fabioJunior.addEndereco(new Endereco(null, null, "14785269", "Rua BRQ", 1525, null, 3, 3));

            ClientePF milionario = new ClientePF(
                null,
                "Romeu Januário de Matos",
                "romeu@gmail.com",
                "dinheiro",
                "12475546853",
                "74585135566",
                "19-04-1972"
            );
            milionario.addTelefone(new Telefone(null, null, "14475455", null, 11, 3));
            milionario.addEndereco(new Endereco(null, null, "01422132", "Rua Bradesco", 145, null, 3, 3));

            ClientePF joseRico = new ClientePF(
                null,
                "José Rico",
                "joserico@gmail.com",
                "fortuna",
                "47987652212",
                "04785236980",
                "14-09-1969"
            );
            joseRico.addTelefone(new Telefone(null, null, "197465328", null, 11, 3));
            joseRico.addEndereco(new Endereco(null, null, "01585236", "Rua Cabeça e Coração", 100, null, 3, 3));


            DAOClientePF daoClientePF = new DAOClientePF(conn);

            daoClientePF.insert(gustavo);
            daoClientePF.insert(robertoCarlos);
            daoClientePF.insert(fabioJunior);
            daoClientePF.insert(milionario);
            daoClientePF.insert(joseRico);

            ClientePJ agrobots = new ClientePJ(
                null,
                "AgroBots Ltda.",
                "bots@agro.com",
                "agro e pop",
                "36974896000157",
                "1234567891245",
                "08-06-2023",
                "Tecnologia"
            );
            agrobots.addTelefone(new Telefone(null, null, "69696969", null, 11, 3));
            agrobots.addEndereco(new Endereco(null, null, "01422356", "Rua Monte Sião", 456, "Próximo a taverna do Simão", 3, 3));


            ClientePJ fiap = new ClientePJ(
                null,
                "FIAP",
                "fiap@tech.com",
                "global solution",
                "36589452843569",
                "7945288563154",
                "20-05-2012",
                "Tecnologia"
            );
            fiap.addTelefone(new Telefone(null, null, "52544236", null, 11, 3));
            fiap.addEndereco(new Endereco(null, null, "48965232", "Rua Silada Bino", 580, "Próximo a Arca", 3, 3));


            ClientePJ positivo = new ClientePJ(
                null,
                "Positivo",
                "positivo@gmail.com",
                "negativo",
                "15889621588521",
                "4868432186842",
                "10-03-2008",
                "Tecnologia"
            );
            positivo.addTelefone(new Telefone(null, null, "52158662", null, 11, 3));
            positivo.addEndereco(new Endereco(null, null, "13259862", "Rua Cade o Baiano", 157, null, 3, 3));


            ClientePJ oboticario = new ClientePJ(
                null,
                "Oboticário",
                "perfumes@oboticario.com",
                "fragancia CR7",
                "13854321505486",
                "1351081321084",
                "25-08-2012",
                "Cosmética"
            );
            oboticario.addTelefone(new Telefone(null, null, "15310084", null, 11, 3));
            oboticario.addEndereco(new Endereco(null, null, "12124508", "Rua Jequiti", 402, null, 3, 3));


            ClientePJ postoIpiranga = new ClientePJ(
                null,
                "Posto Ipiranga",
                "posto@ipiranga.com",
                "pergunta la",
                "02351681305183",
                "51208151504562",
                "30-01-2005",
                "Petrolífera"
            );
            postoIpiranga.addTelefone(new Telefone(null, null, "52058130", null, 11, 3));
            postoIpiranga.addEndereco(new Endereco(null, null, "54138508", "Rua Irineu", 205, "Logo ali", 3, 3));


            DAOClientePJ daoClientePJ = new DAOClientePJ(conn);

            daoClientePJ.insert(agrobots);
            daoClientePJ.insert(fiap);
            daoClientePJ.insert(positivo);
            daoClientePJ.insert(oboticario);
            daoClientePJ.insert(postoIpiranga);

            printClientesPF(daoClientePF.getAll());
            printClientesPJ(daoClientePJ.getAll());

            DAODespesa daoDespesa = new DAODespesa(conn);

            Despesa boletoClaro = new Despesa(null, 30, 3, "Conta claro", 120.27, "Conta referente 10/2023", "27-10-2023");
            Despesa boletoCopasa = new Despesa(null, 34, 1, "Copasa copasa", 500.00, "Conta referente 10/2023", "15-10-2023");
            Despesa boletoEnel = new Despesa(null, 29, 2, "Conta enel", 210.94, "Conta referente 10/2023", "21-10-2023");
            Despesa boletoSabesp = new Despesa(null, 33, 1, "Conta sabesp", 800.00, "Conta referente 10/2023", "05-10-2023");
            Despesa boletoTim = new Despesa(null, 36, 3, "Conta tim", 647.99, "Conta referente 10/2023", "08-10-2023");

            daoDespesa.insert(boletoClaro);
            daoDespesa.insert(boletoCopasa);
            daoDespesa.insert(boletoEnel);
            daoDespesa.insert(boletoSabesp);
            daoDespesa.insert(boletoTim);

            printDespesas(daoDespesa.getAll());

            DAOReceita daoReceita = new DAOReceita(conn);

            Receita entradaPensao = new Receita(null, 31, 3, 500.00, "01-11-2023");
            Receita entradaBolsa = new Receita(null, 33, 1, 1500.00, "25-10-2023");
            Receita entradaSalario = new Receita(null, 30, 2, 2500.00, "07-10-2023");
            Receita entradaPensaoMensal = new Receita(null, 32, 3, 15000.00, "15-10-2023");
            Receita entradaBolsaValores = new Receita(null, 36, 2, 1000.00, "27-10-2023");

            
            daoReceita.insert(entradaPensao);
            daoReceita.insert(entradaBolsa);
            daoReceita.insert(entradaSalario);
            daoReceita.insert(entradaPensaoMensal);
            daoReceita.insert(entradaBolsaValores);


            printReceita(daoReceita.getAll());

        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível se conectar");
            e.printStackTrace();
        }
    }

    private static void printClientesPF(List<ClientePF> list)
    {
        System.out.println("-------CLIENTES PF-------\n");

        for (ClientePF cliente : list)
        {
            System.out.println("ID: " + cliente.getId());
            System.out.println("NOME: " + cliente.getNome());

            System.out.println("TELEFONES");
            for (Telefone telefone : cliente.getTelefones())
            {
                System.out.println("\t" + telefone.getDdd() + " " + telefone.getNumero());
            }

            System.out.println("ENDEREÇOS");
            for (Endereco endereco : cliente.getEnderecos())
            {
                System.out.println("\t" + endereco.getLogradouro() + " " + endereco.getNumero());
            }
            System.out.println("\n");
        }
    }

    private static void printClientesPJ(List<ClientePJ> list)
    {
        System.out.println("-------CLIENTES PJ-------\n");

        for (ClientePJ cliente : list)
        {
            System.out.println("ID: " + cliente.getId());
            System.out.println("NOME: " + cliente.getNome());

            System.out.println("TELEFONES");
            for (Telefone telefone : cliente.getTelefones())
            {
                System.out.println("\t" + telefone.getDdd() + " " + telefone.getNumero());
            }

            System.out.println("ENDEREÇOS");
            for (Endereco endereco : cliente.getEnderecos())
            {
                System.out.println("\t" + endereco.getLogradouro() + " " + endereco.getNumero());
            }
            System.out.println("\n");
        }
    }

    private static void printDespesas(List<Despesa> list)
    {
        System.out.println("-------DESPESAS-------\n");

        for (Despesa despesa : list)
        {
            System.out.println("ID: " + despesa.getId());
            System.out.println("NOME: " + despesa.getNome());
            System.out.println("VALOR: R$" + despesa.getValor());
            System.out.println("ID DO TIPO DE DESPESA: " + despesa.getIdTipoDespesa());
            System.out.println("\n");
        }
    }


    private static void printReceita(List<Receita> list)
    {
        System.out.println("-------RECEITAS-------\n");

        for (Receita receita : list)
        {
            System.out.println("ID: " + receita.getId());
            System.out.println("DATA: " + receita.getDataReceita());
            System.out.println("VALOR: R$" + receita.getValor());
            System.out.println("ID DO TIPO DE RECEITA: " + receita.getIdTipoReceita());
            System.out.println("ID DO CLIENTE: " + receita.getIdCliente());            
            System.out.println("\n");
        }
    }
}
